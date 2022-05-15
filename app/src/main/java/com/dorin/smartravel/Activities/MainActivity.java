package com.dorin.smartravel.Activities;

import com.dorin.smartravel.DataManger;
import com.dorin.smartravel.Fragments.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.dorin.smartravel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout main_drawerLayout;
    private MaterialToolbar main_Toolbar_Top;
    private FragmentContainerView main_fragment;
    private BottomNavigationView main_bottomNavigationView;
    private BottomAppBar main_bottomAppBar;
    private FloatingActionButton main_fab;
    private NavigationView main_TopNavigationView;

    public static final int UPCOMING = 0,HISTORY = 1, MY_PLACES = 2, DAYS_PATH_TRIP = 3, DAY_TRIP = 4,SETTINGS = 5, PROFILE=6;
    private final int SIZE=7;
    private Fragment[] main_fragments;
    private FragmentManager fragmentManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setSupportActionBar(main_bottomAppBar);
        setSupportActionBar(main_Toolbar_Top);
        initColorMenu();
        setFragments();
        initButtons();
        replaceFragments(main_fragments[UPCOMING]);

    }


    private void findViews() {

        main_drawerLayout=findViewById(R.id.main_drawerlayout);
        main_Toolbar_Top=findViewById(R.id.main_Toolbar_Top);
        main_fragment=findViewById(R.id.main_fragment);
        main_bottomNavigationView=findViewById(R.id.main_bottomNavigationView);
        main_bottomNavigationView.setBackground(null);
        main_bottomAppBar=findViewById(R.id.main_bottomAppBar);
        main_fab=findViewById(R.id.main_fab);
        main_TopNavigationView=findViewById(R.id.main_TopNavigationView);

    }


    private void initButtons() {

        main_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceActivity(CreateNewTripActivity.class);
            }
        });

        main_Toolbar_Top.setNavigationOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                main_drawerLayout.openDrawer(main_drawerLayout.getForegroundGravity());
            }
        });



        main_bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_upcoming:
                        main_Toolbar_Top.setTitle(R.string.Upcoming);
                        replaceFragments(main_fragments[UPCOMING]);
                        break;
                    case R.id.menu_history:
                        main_Toolbar_Top.setTitle(R.string.History);
                        replaceFragments(main_fragments[HISTORY]);
                        break;
                    case R.id.menu_myPlaces:
                        main_Toolbar_Top.setTitle(R.string.MyPlaces);
                        //replaceFragments(main_fragments[MY_PLACES]);
                        replaceActivity(MyPlacesActivity.class);
                        break;
                    case R.id.menu_profile:
                        main_Toolbar_Top.setTitle(R.string.Profile);
                        replaceFragments(main_fragments[PROFILE]);
                        break;
                }
                return true;
            }
        });


        main_TopNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.menu_upcoming:
                        main_Toolbar_Top.setTitle(R.string.Upcoming);
                        replaceFragments(main_fragments[UPCOMING]);
                        break;
                    case R.id.menu_history:
                        main_Toolbar_Top.setTitle(R.string.History);
                        replaceFragments(main_fragments[HISTORY]);
                        break;
                    case R.id.menu_myPlaces:
                        main_Toolbar_Top.setTitle(R.string.MyPlaces);
                        //replaceFragments(main_fragments[MY_PLACES]);
                        replaceActivity(MyPlacesActivity.class);
                        break;
                    case R.id.menu_profile:
                        main_Toolbar_Top.setTitle(R.string.Profile);
                        replaceFragments(main_fragments[PROFILE]);
                        break;
                    case R.id.menu_settings:
                        main_Toolbar_Top.setTitle(R.string.Settings);
                        replaceFragments(main_fragments[SETTINGS]);
                        break;
                    case R.id.menu_logout:
                        DataManger.getInstance().getmGoogleSignInClient().signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                replaceActivity(LoginActivity.class);
                                finish();
                            }
                        });
                        break;
                }
                return true;
            }
        });

    }

    private void initColorMenu() {


        int navDefaultTextColor = Color.parseColor("#FFFFFFFF");
        int navDefaultIconColor = Color.parseColor("#FFFFFFFF");

        //Defining ColorStateList for menu item Text
        ColorStateList navMenuTextList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int []{
                        Color.parseColor("#4B9226"),
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor,
                        navDefaultTextColor
                }
        );

        //Defining ColorStateList for menu item Icon
        ColorStateList navMenuIconList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_pressed}
                },
                new int[] {
                        Color.parseColor("#4B9226"),
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor,
                        navDefaultIconColor
                }
        );

        main_TopNavigationView.setItemTextColor(navMenuTextList);
        main_TopNavigationView.setItemIconTintList(navMenuIconList);

    }




    private void setFragments() {
        main_fragments = new Fragment[SIZE];
        main_fragments[UPCOMING] = new UpcomingFragment().setActivity(this);
        main_fragments[HISTORY] = new HistoryFragment().setActivity(this);
        main_fragments[MY_PLACES] = new MyPlacesFragment().setActivity(this);
        main_fragments[DAYS_PATH_TRIP] = new DaysPathTripFragment().setActivity(this);
        main_fragments[DAY_TRIP] = new DayTripFragment().setActivity(this);
        main_fragments[SETTINGS] = new SettingsFragment().setActivity(this);
        main_fragments[PROFILE] = new ProfileFragment().setActivity(this);

    }

    private void replaceFragments(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, fragment, null).commit();
    }



    private void replaceActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }



}