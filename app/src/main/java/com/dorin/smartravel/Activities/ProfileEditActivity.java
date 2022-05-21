package com.dorin.smartravel.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

//  private AutoCompleteTextView EditProfile_AutoCompleteTextView;
//  private ArrayAdapter<String> adapterItems;
//  private String yearSelected;
//  private String[] yearsItems;
//  private TextInputEditText EditProfile_LBL_EditEmail;
//  private RadioButton radioMale;
//  private RadioButton radioFemale;



    private MaterialButton EditProfile_BTN_Update;
    private MaterialToolbar EditProfile_toolBar;
    private FloatingActionButton EditProfile_FAB_edit_user_IMG;
    private CircleImageView EditProfile_IMG_User;
    private TextInputLayout EditProfile_LBL_FirstName;
    private TextInputEditText EditProfile_LBL_EditFirstName;
    private TextInputLayout EditProfile_LBL_LastName;
    private TextInputEditText EditProfile_LBL_EditLastName;
    private RadioGroup EditProfile_RG_Gender;

    private DataManger dataManger = DataManger.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
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
        EditProfile_BTN_Update = findViewById(R.id.EditProfile_BTN_Update);

        EditProfile_LBL_EditFirstName.setText(dataManger.getCurrentUser().getFirstName());
        EditProfile_LBL_EditLastName.setText(dataManger.getCurrentUser().getLastName());
//        EditProfile_RG_Gender = findViewById(R.id.EditProfile_RG_Gender);
//        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,yearsItems);
//        EditProfile_AutoCompleteTextView.setAdapter(adapterItems);
//        EditProfile_AutoCompleteTextView = findViewById(R.id.EditProfile_AutoCompleteTextView);
//        TextInputLayout editProfile_LBL_Email = findViewById(R.id.EditProfile_LBL_Email);
//        EditProfile_LBL_EditEmail = findViewById(R.id.EditProfile_LBL_EditEmail);
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
                choseCover();
            }
        });


//        EditProfile_AutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // TODO: 4/24/2022 add on click update year birth
//                yearSelected = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Year: "+yearSelected, Toast.LENGTH_SHORT).show();
//            }
//        });


//        EditProfile_RG_Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                // TODO: 4/24/2022 Add update gender user
//                RadioButton button = (RadioButton)radioGroup.findViewById(i);
//                if(button != null && i != -1)
//                {
//                    Toast.makeText(ProfileEditActivity.this, button.getText()+"\t is selected", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        EditProfile_BTN_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 4/24/2022 Add update profile
                finish();
            }
        });
    }


    private void choseCover() {
        ImagePicker.with(ProfileEditActivity.this)
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .crop(1f, 1f)
                .maxResultSize(1080, 1080)
                //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    /**
     *Results From ImagePicker will be catch here
     * will place the image in the relevant Image View
     * Right after that, will catch the image bytes back from the view and store them in the Firebase Storage.
     * After successful upload will update the Object Url Field
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }



}


