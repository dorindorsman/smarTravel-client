package com.dorin.smartravel.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dorin.smartravel.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SplashActivity extends AppCompatActivity {

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
        Log.d("pttt","line 58");



//        //Splash Screen
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                    replaceActivity(MainActivity.class);
//            }
//        },SPLASH_TIME_OUT);

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
        //splash_IMG_Icon.setAnimation(middleAnimation);
        //splash_LBL_Android.setAnimation(bottomAnimation);
        splash_IMG_Icon.animate().translationY(0).setDuration(3000).setStartDelay(500);
        splash_LBL_Android.animate().translationY(0).setDuration(3000).setStartDelay(500)
                .setInterpolator(new AnticipateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d("pttt","line 110");

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d("pttt","line 116");
                // Check for existing Google Sign In account, if the user is already signed in
                // the GoogleSignInAccount will be non-null.
                account = GoogleSignIn.getLastSignedInAccount(SplashActivity.this);
                //Log.d("pttt",account.toString());
                updateUI(account);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


    private void replaceActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


    private void updateUI(GoogleSignInAccount account) {
        if(account!=null){
            replaceActivity(MainActivity.class);
        }else{
            replaceActivity(LoginActivity.class);
        }
    }



}