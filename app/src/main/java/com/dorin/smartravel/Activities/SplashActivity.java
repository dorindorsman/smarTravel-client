package com.dorin.smartravel.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorin.smartravel.Helpers.DataManger;
import com.dorin.smartravel.Helpers.LocationManager;
import com.dorin.smartravel.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=5000;


    private ImageView splash_IMG_Icon;
    private TextView  splash_LBL_Android;

    //Animations
    Animation topAnimation, bottomAnimation, middleAnimation;

    private DataManger dataManger;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViews();
        initAnimation();

        dataManger = DataManger.getInstance();
        locationManager = new LocationManager(this);
        locationManager.setCallBackLocation(callBackLocation);
        locationManager.getLocationPermission();
        dataManger.fetchDataFromUrl.start();
    }

    LocationManager.CallBackLocation callBackLocation = new LocationManager.CallBackLocation() {
        @Override
        public void locationReady(double longitude, double latitude) {
            Log.d("roman",""+longitude+"  "+latitude);
            dataManger.setCurrentLocation(longitude, latitude);
            replaceActivity(LoginActivity.class);
        }
    };



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationManager.getLocation();
    }


    private void findViews() {

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        splash_IMG_Icon=findViewById(R.id.splash_IMG_Icon);
        splash_LBL_Android=findViewById(R.id.splash_LBL_Android);

    }


    private void initAnimation() {

        splash_IMG_Icon.animate().translationY(0).setDuration(3000).setStartDelay(500);
        splash_LBL_Android.animate().translationY(0).setDuration(3000).setStartDelay(500)
                .setInterpolator(new AnticipateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                locationManager.getLocation();
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

}