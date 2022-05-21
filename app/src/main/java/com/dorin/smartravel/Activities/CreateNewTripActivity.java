package com.dorin.smartravel.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreateNewTripActivity extends AppCompatActivity {

    private MaterialToolbar CreateTrip_toolBar;
    private TextInputLayout createTrip_TIN_destination;
    private TextInputLayout createTrip_TIN_StartDate;
    private TextInputLayout createTrip_TIN_EndDate;
    private MaterialButton createTrip_BTN_Create;

    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    private Date start;
    private Date end;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private DataManger dataManger = DataManger.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);

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


        createTrip_BTN_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    start=format.parse(createTrip_TIN_StartDate.getEditText().getText().toString());
                    end=format.parse(createTrip_TIN_EndDate.getEditText().getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long msDiff = end.getTime() - start.getTime();
                long daysDiff = (msDiff/(1000*60*60*24)+1);

                dataManger.getCurrentTrip().setName(createTrip_TIN_destination.getEditText().getText().toString());
                dataManger.getCurrentTrip().setNumOfDays((int)daysDiff);
                dataManger.getCurrentTrip().setThumbnail(R.drawable.ic_logo);
                dataManger.getCurrentTrip().setStartDate(start.toString());
                dataManger.getCurrentTrip().setEndDate(end.toString());

            }
        });

//        createTrip_TIN_StartDate.getEditText().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MaterialDatePicker<Pair<Long, Long>> startDatePicker =  MaterialDatePicker.Builder.dateRangePicker().build();
//                startDatePicker.show(getSupportFragmentManager(),"DATE_RANGE_PICKER");
//                startDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
//                    @Override
//                    public void onPositiveButtonClick(Object selection) {
//                        createTrip_TIN_StartDate.getEditText().setText(selection.toString());
//                    }
//                });
//            }
//        });




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

    private void findViews() {

        CreateTrip_toolBar = findViewById(R.id.CreateTrip_toolBar);
        createTrip_TIN_destination = findViewById(R.id.createTrip_TIN_destination);
        createTrip_TIN_StartDate= findViewById(R.id.createTrip_TIN_StartDate);
        createTrip_TIN_EndDate= findViewById(R.id.createTrip_TIN_EndDate);
        createTrip_BTN_Create= findViewById(R.id.createTrip_BTN_Create);

    }
}