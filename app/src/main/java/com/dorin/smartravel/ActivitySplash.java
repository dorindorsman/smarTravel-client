package com.dorin.smartravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ActivitySplash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=5000;


    private ImageView splash_IMG_Icon;
    private TextView  splash_LBL_Android;


    //Animations
    Animation topAnimation, bottomAnimation, middleAnimation;


    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        findViews();
        initAnimation();


        // TODO: 4/22/2022  add check if work
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    replaceActivity(MainActivity.class);
            }
        },SPLASH_TIME_OUT);

//        //Splash Screen
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//                FirebaseDB firebaseDB=FirebaseDB.getInstance();
//                if(firebaseAuth.getCurrentUser() != null){
//                    firebaseDB.hasProfile(firebaseAuth.getCurrentUser().getUid());
//                    splash_progressBar.setVisibility(View.VISIBLE);
//                }else{
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        },SPLASH_TIME_OUT);

    }

    private void findViews() {

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        splash_IMG_Icon=findViewById(R.id.splash_IMG_Icon);
        splash_LBL_Android=findViewById(R.id.splash_LBL_Android);

    }


    private void initAnimation() {
        splash_IMG_Icon.setAnimation(middleAnimation);
        splash_LBL_Android.setAnimation(bottomAnimation);
    }


    private void replaceActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


    // TODO: 4/22/2022 Add - check if work 
    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account!=null){
            replaceActivity(MainActivity.class);
        }else{
            replaceActivity(LoginActivity.class);
        }
    }



}