package com.dorin.smartravel.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.User;
import com.dorin.smartravel.R;
import com.dorin.smartravel.retrofit.UserApi;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;


    private SignInButton signInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // TODO: 4/22/2022  add check if work
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        DataManger.getInstance().setGso(gso);

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        DataManger.getInstance().setmGoogleSignInClient(mGoogleSignInClient);



        findViews();
        initButton();


    }


    private void findViews() {
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
    }


    private void initButton() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                someActivityResultLauncher.launch(signInIntent);
            }
        });

    }



    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        handleSignInResult(task);
                    }
                }
            });

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("TAG", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }


    private void signIn() {

        CreateUser();
        replaceActivity(MainActivity.class);
        //Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //someActivityResultLauncher.launch();
    }

    // TODO: 5/4/2022 move to data manager
    private void CreateUser() {

        User user= new User("IMG","dorin","dorsman","dorinDorsman","dorsmandorin@gmail.com","1995","Female");
//        JSONObject userJson= user.convertToJson();
//        Log.d("ptt",userJson+" ");
        UserApi userApi= DataManger.getInstance().getRetrofitService().getRetrofit().create(UserApi.class);
        userApi.createUser(user).
                enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Log.d("ptt","Save Successful");
                Log.d("ptt",response.body().getClass().getName()+"");
                String domain = ((LinkedTreeMap)((LinkedTreeMap) response.body()).get("userId")).get("domain").toString();
                //String a = ((LinkedTreeMap) domain).get("domain").toString();

                String b =((LinkedTreeMap<String, LinkedTreeMap>) response.body()).get("userId").get("domain").toString();
                Log.d("ptt",domain);
                Log.d("ptt",b);
                try {
                    Log.d("ptt",""+response.body());
                }catch (Exception e){
                    Log.d("ptt",e.toString());
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("ptt","Save Failed");
                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE , "Error occurred",t);
            }
        });

        createInstanceUser();

    }

    // TODO: 5/4/2022 move to data manager
    private void createInstanceUser() {
//         user= new User("IMG","dorin","dorsman","dorinDorsman","dorsmandorin@gmail.com","1995","Female");
//        UserApi userApi= DataManger.getInstance().getRetrofitService().getRetrofit().create(UserApi.class);
//        userApi.createUser(user).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                //  Toast.makeText(LoginActivity.this, "Save Successful", Toast.LENGTH_SHORT).show();
//                Log.d("ptt","Save Successful");
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.d("ptt","Save Failed");
//                //  Log.dLoginActivity.this,"Save Failed",Toast.LENGTH_SHORT).show();
//                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE , "Error occurred",t);
//            }
//        });


    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);


        if(account!=null){
            DataManger.getInstance().setAccount(account);
            replaceActivity(MainActivity.class);
        }

    }

    private void replaceActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}