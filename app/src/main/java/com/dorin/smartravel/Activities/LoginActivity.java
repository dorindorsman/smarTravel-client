package com.dorin.smartravel.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Objects.User;
import com.dorin.smartravel.R;
import com.dorin.smartravel.retrofit.Convertor;
import com.dorin.smartravel.retrofit.UserApi;
import com.dorin.smartravel.serverObjects.CreatedBy;
import com.dorin.smartravel.serverObjects.DomainWithEmail;
import com.dorin.smartravel.serverObjects.InstanceBoundary;
import com.dorin.smartravel.serverObjects.UserBoundary;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.internal.LinkedTreeMap;


import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;
    private DataManger dataManger;
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

        dataManger = DataManger.getInstance();

        dataManger.setGso(gso);

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        dataManger.setmGoogleSignInClient(mGoogleSignInClient);

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
            account = task.getResult(ApiException.class);
            signIn();

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

        putAccountIntoUser();
        CreateUser();
        replaceActivity(MainActivity.class);
        //Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //someActivityResultLauncher.launch();
    }

    private void putAccountIntoUser() {
        if(account.getPhotoUrl()==null){
            dataManger.getCurrentUser()
                    .setFirstName(account.getGivenName())
                    .setLastName(account.getFamilyName())
                    .setAvatar("empty")
                    .setEmail(account.getEmail());
        }else{
            dataManger.getCurrentUser()
                    .setFirstName(account.getGivenName())
                    .setLastName(account.getFamilyName())
                    .setAvatar(account.getPhotoUrl().toString())
                    .setEmail(account.getEmail());
        }
    }

    // TODO: 5/4/2022 move to data manager
    private void CreateUser() {
        UserBoundary userboundary = Convertor.convertUserToUserBoundary(dataManger.getCurrentUser());
        UserApi userApi= dataManger.getRetrofitService().getRetrofit().create(UserApi.class);
        Log.d("userBoundary",userboundary.toString());
        userApi.createUser(userboundary)
                .enqueue(new Callback<UserBoundary>() {
            @Override
            public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
                try {
                    dataManger.getCurrentUser().setDomain(response.body().getUserId().getDomain());
                    Log.d("userBoundary",dataManger.getCurrentUser().toString());
                }catch (Exception e){
                    Log.d("error",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserBoundary> call, Throwable t) {

            }
        });
        createInstanceUser();
    }



    private void createInstanceUser() {

        InstanceBoundary instanceBoundary = Convertor.convertUserToInstanceBoundary(dataManger.getCurrentUser());
        UserApi userApi= dataManger.getRetrofitService().getRetrofit().create(UserApi.class);
        userApi.createInstance(instanceBoundary)
                .enqueue(new Callback<InstanceBoundary>() {
            @Override
            public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {

                try {
                    dataManger.getMyInstances().put("user",response.body().getInstanceId().getId());
                    Log.d("InstanceId",dataManger.getMyInstances().get("user"));
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<InstanceBoundary> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            dataManger.setAccount(account);
            putAccountIntoUser();
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