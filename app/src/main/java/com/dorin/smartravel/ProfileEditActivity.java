package com.dorin.smartravel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileEditActivity extends AppCompatActivity {

    private AutoCompleteTextView EditProfile_AutoCompleteTextView;
    private ArrayAdapter<String> adapterItems;
    private String[] yearsItems;

    private TextInputLayout EditProfile_LBL_FirstName;
    private TextInputEditText EditProfile_LBL_EditFirstName;
    private TextInputLayout EditProfile_LBL_LastName;
    private TextInputEditText EditProfile_LBL_EditLastName;
    private TextInputLayout EditProfile_LBL_Email;
    private TextInputEditText EditProfile_LBL_EditEmail;
    private Switch EditProfile_simpleSwitch_Gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
       // yearsItems= getResources().getStringArray(R.array.year_of_birth);
        yearsItems=new String[]{"1", "2", "three"};;



        findViews();
        initEditFields();
    }

    private void findViews() {

        EditProfile_LBL_FirstName = findViewById(R.id.EditProfile_LBL_FirstName);
        EditProfile_LBL_EditFirstName = findViewById(R.id.EditProfile_LBL_EditFirstName);
        EditProfile_LBL_LastName = findViewById(R.id.EditProfile_LBL_LastName);
        EditProfile_LBL_EditLastName = findViewById(R.id.EditProfile_LBL_EditLastName);
        EditProfile_LBL_Email = findViewById(R.id.EditProfile_LBL_Email);
        EditProfile_LBL_EditEmail = findViewById(R.id.EditProfile_LBL_EditEmail);
        EditProfile_AutoCompleteTextView = findViewById(R.id.EditProfile_AutoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,yearsItems);
        EditProfile_AutoCompleteTextView.setAdapter(adapterItems);
        EditProfile_simpleSwitch_Gender = findViewById(R.id.EditProfile_simpleSwitch_Gender);

    }

    private void initEditFields() {


        EditProfile_AutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String yearSelected = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Year: "+yearSelected, Toast.LENGTH_SHORT).show();
            }
        });

        EditProfile_simpleSwitch_Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





    }






    }


