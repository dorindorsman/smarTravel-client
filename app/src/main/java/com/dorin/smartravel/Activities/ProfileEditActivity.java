package com.dorin.smartravel.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dorin.smartravel.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

    private AutoCompleteTextView EditProfile_AutoCompleteTextView;
    private ArrayAdapter<String> adapterItems;
    private String[] yearsItems;
    private String yearSelected;

    private MaterialToolbar EditProfile_toolBar;
    private FloatingActionButton EditProfile_FAB_edit_user_IMG;
    private CircleImageView EditProfile_IMG_User;
    private TextInputLayout EditProfile_LBL_FirstName;
    private TextInputEditText EditProfile_LBL_EditFirstName;
    private TextInputLayout EditProfile_LBL_LastName;
    private TextInputEditText EditProfile_LBL_EditLastName;
    private TextInputEditText EditProfile_LBL_EditEmail;
    private RadioGroup EditProfile_RG_Gender;
   // private RadioButton radioMale;
   // private RadioButton radioFemale;
    private MaterialButton EditProfile_BTN_Update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        yearsItems=new String[]{"1980", "1981", "1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992",
                "1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008",
        "2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022"};



        findViews();
        initEditFields();
    }

    private void findViews() {

        EditProfile_toolBar = findViewById(R.id.EditProfile_toolBar);
        EditProfile_FAB_edit_user_IMG = findViewById(R.id.EditProfile_FAB_edit_user_IMG);
        EditProfile_IMG_User = findViewById(R.id.EditProfile_IMG_User);
        EditProfile_LBL_FirstName = findViewById(R.id.EditProfile_LBL_FirstName);
        EditProfile_LBL_EditFirstName = findViewById(R.id.EditProfile_LBL_EditFirstName);
        EditProfile_LBL_LastName = findViewById(R.id.EditProfile_LBL_LastName);
        EditProfile_LBL_EditLastName = findViewById(R.id.EditProfile_LBL_EditLastName);
        TextInputLayout editProfile_LBL_Email = findViewById(R.id.EditProfile_LBL_Email);
        EditProfile_LBL_EditEmail = findViewById(R.id.EditProfile_LBL_EditEmail);
        EditProfile_AutoCompleteTextView = findViewById(R.id.EditProfile_AutoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,yearsItems);
        EditProfile_AutoCompleteTextView.setAdapter(adapterItems);
        EditProfile_RG_Gender = findViewById(R.id.EditProfile_RG_Gender);
        EditProfile_BTN_Update = findViewById(R.id.EditProfile_BTN_Update);


    }

    private void initEditFields() {


        EditProfile_toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EditProfile_FAB_edit_user_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 4/24/2022 add on click edit image view and update user 
            }
        });


        EditProfile_AutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 4/24/2022 add on click update year birth
                yearSelected = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Year: "+yearSelected, Toast.LENGTH_SHORT).show();
            }
        });


        EditProfile_RG_Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // TODO: 4/24/2022 Add update gender user 
                RadioButton button = (RadioButton)radioGroup.findViewById(i);
                if(button != null && i != -1)
                {
                    Toast.makeText(ProfileEditActivity.this, button.getText()+"\t is selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


        EditProfile_BTN_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 4/24/2022 Add update profile
                finish();
            }
        });



    }






    }


