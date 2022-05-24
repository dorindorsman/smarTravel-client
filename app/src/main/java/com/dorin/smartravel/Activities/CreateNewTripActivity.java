package com.dorin.smartravel.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.DayTrip;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.dorin.smartravel.retrofit.Convertor;
import com.dorin.smartravel.retrofit.UserApi;
import com.dorin.smartravel.serverObjects.ActivityBoundary;
import com.dorin.smartravel.serverObjects.InstanceBoundary;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewTripActivity extends AppCompatActivity {

    private MaterialToolbar CreateTrip_toolBar;
    private TextInputLayout createTrip_TIN_destination;
    private AutoCompleteTextView createTrip_AutoCompleteTextViewDestination;
    private TextInputLayout createTrip_TIN_StartDate;
    private TextInputLayout createTrip_TIN_EndDate;
    private MaterialButton createTrip_BTN_Create;

    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    private Date start;
    private Date end;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private DataManger dataManger;

    private static final String[] destinations = new String[] {
            "Paris", "Barcelona", "New York", "Melbourne", "Los Angels"};
    private String destinationSelected;

    private ArrayAdapter<String> destinationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);

        dataManger = DataManger.getInstance();
        start = new Date();
        end = new Date();

        findViews();
        initFields();

    }

    private void initFields() {

        CreateTrip_toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        createTrip_AutoCompleteTextViewDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 4/24/2022 add on click update year birth
                destinationSelected = parent.getItemAtPosition(position).toString();
            }
        });

        createTrip_BTN_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    start=format.parse(createTrip_TIN_StartDate.getEditText().getText().toString());
                    end=format.parse(createTrip_TIN_EndDate.getEditText().getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                findTripFromDataManager();
                finish();
//                dataManger.getCurrentTrip().setName(createTrip_TIN_destination.getEditText().getText().toString());
//                dataManger.getCurrentTrip().setNumOfDays((int)daysDiff);
//                dataManger.getCurrentTrip().setThumbnail(R.drawable.ic_logo);
//                dataManger.getCurrentTrip().setStartDate(start.toString());
//                dataManger.getCurrentTrip().setEndDate(end.toString());

            }
        });


        createTrip_TIN_StartDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                //start = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                startDatePickerDialog = new DatePickerDialog(CreateNewTripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                createTrip_TIN_StartDate.getEditText().setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                //start.setTime(c.getTimeInMillis());

                startDatePickerDialog.show();

            }
        });

        createTrip_TIN_EndDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                //end=Calendar.getInstance();
                int mYear = startDatePickerDialog.getDatePicker().getYear(); // current year
                int mMonth = startDatePickerDialog.getDatePicker().getMonth(); // current month
                int mDay = startDatePickerDialog.getDatePicker().getDayOfMonth(); // current day
                c.set(mYear,mMonth,mDay);
                // date picker dialog
                endDatePickerDialog = new DatePickerDialog(CreateNewTripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                createTrip_TIN_EndDate.getEditText().setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                //end.setTime(c.getTimeInMillis());

                endDatePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                endDatePickerDialog.show();

            }
        });
    }

    private void findTripFromDataManager() {

        for (int i=0; i<dataManger.fetchDataFromUrl.trips.length;i++){
            if(dataManger.fetchDataFromUrl.trips[i].getName().equals(destinationSelected)){
                Trip trip=new Trip(dataManger.fetchDataFromUrl.trips[i]);
                trip.setStartDate(createTrip_TIN_StartDate.getEditText().getText().toString());
                trip.setEndDate(createTrip_TIN_EndDate.getEditText().getText().toString());
                long msDiff = end.getTime() - start.getTime();
                long daysDiff = (msDiff/(1000*60*60*24)+1);
                trip.setNumOfDays((int)daysDiff);
                trip.setIsRate(false);
                while(trip.getDayTripList().size()>trip.getNumOfDays()){
                    trip.getDayTripList().remove(trip.getDayTripList().size()-1);
                }
                for (DayTrip day:trip.getDayTripList()) {
                    // DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
                   // String formattedDate = formatter.format(start);
                    // FIXME: 5/24/2022 fix date 
                    day.setDate(start.toString());
                    day.setTitle();
                    Calendar c = Calendar.getInstance();
                    c.setTime(start);
                    c.add(Calendar.DATE, 1);
                    start = c.getTime();
                }
                dataManger.getTripList().add(trip);
                dataManger.getCallBackCreateTrip().createTrip();
                Toast.makeText(CreateNewTripActivity.this,"Trip Added Successfully",Toast.LENGTH_LONG).show();
                createInstanceTrip(trip);

            }
        }

    }

    private void createInstanceTrip(Trip trip) {
        InstanceBoundary instanceBoundary = Convertor.convertTripToInstanceBoundary(trip);
        UserApi userApi= dataManger.getRetrofitService().getRetrofit().create(UserApi.class);
        userApi.createInstance(instanceBoundary)
                .enqueue(new Callback<InstanceBoundary>() {
                    @Override
                    public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
                        try {
                            dataManger.getMyInstances().put("trip"+dataManger.getInstanceTripCounter(),response.body().getInstanceId().getId());
                            createActivityBoundary();
                            dataManger.setInstanceTripCounter(dataManger.getInstanceTripCounter()+1);
                        }catch (Exception e){
                            Log.d("error",e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<InstanceBoundary> call, Throwable t) {

                    }
                });
    }


    private void createActivityBoundary() {
        ActivityBoundary activityBoundary = Convertor.convertToActivityBoundary(dataManger.getCurrentUser().getDomain(),dataManger.getMyInstances().get("trip"+dataManger.getInstanceTripCounter()),dataManger.getCurrentUser().getDomain(),dataManger.getCurrentUser().getEmail(),"newTrip");
        UserApi userApi= dataManger.getRetrofitService().getRetrofit().create(UserApi.class);
        userApi.createActivity(activityBoundary)
                .enqueue(new Callback<ActivityBoundary>() {
                    @Override
                    public void onResponse(Call<ActivityBoundary> call, Response<ActivityBoundary> response) {

                    }

                    @Override
                    public void onFailure(Call<ActivityBoundary> call, Throwable t) {

                    }
                });
    }

    private void findViews() {

        CreateTrip_toolBar = findViewById(R.id.CreateTrip_toolBar);
        createTrip_TIN_destination = findViewById(R.id.createTrip_TIN_destination);
        createTrip_AutoCompleteTextViewDestination = findViewById(R.id.createTrip_AutoCompleteTextViewDestination);
        destinationAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, destinations);
        createTrip_AutoCompleteTextViewDestination.setAdapter(destinationAdapter);
        createTrip_TIN_StartDate= findViewById(R.id.createTrip_TIN_StartDate);
        createTrip_TIN_EndDate= findViewById(R.id.createTrip_TIN_EndDate);
        createTrip_BTN_Create= findViewById(R.id.createTrip_BTN_Create);

    }
}