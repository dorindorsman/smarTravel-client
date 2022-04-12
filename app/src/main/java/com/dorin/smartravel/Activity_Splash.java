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

public class Activity_Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=5000;


    private ImageView splash_IMG_Icon;
    private TextView  splash_LBL_Android;


    //Animations
    Animation topAnimation, bottomAnimation, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        findViews();
        initAnimation();


        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    replaceActivity();
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


    private void replaceActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}