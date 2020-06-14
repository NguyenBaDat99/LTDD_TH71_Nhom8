package com.example.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dictionary.ChangeTheme.AppPreferenceManager;
import com.example.dictionary.fragment.LuyenDocFragment;
import com.example.dictionary.fragment.LuyenNgheFragment;
import com.example.dictionary.fragment.TraCuuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    final Fragment fragmentLuyenNghe = new LuyenNgheFragment();
    final Fragment fragmentTraCuu = new TraCuuFragment();
    final Fragment fragmentLuyenDoc = new LuyenDocFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragmentActive = fragmentTraCuu;


    BottomNavigationView bottomNavigationView;
    AppPreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {







        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()) {
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);


        //bottom_Navigation_Menu
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //Load fragment TraCuu
        fragmentManager.beginTransaction().add(R.id.fragmentHienThi, fragmentLuyenDoc, "LuyenDoc").hide(fragmentLuyenDoc).commit();
        fragmentManager.beginTransaction().add(R.id.fragmentHienThi, fragmentLuyenNghe, "LuyenNghe").hide(fragmentLuyenNghe).commit();
        fragmentManager.beginTransaction().add(R.id.fragmentHienThi, fragmentTraCuu, "TraCuu").commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
            {
                Intent intent = new Intent(Main.this, SettingsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.action_about:
            {
                Intent intent = new Intent(Main.this, AboutActivity.class);
                startActivity(intent);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Khởi tạo Bottom_Navigation_Menu
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_tracuu:{
                            fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentTraCuu).commit();
                            fragmentActive = fragmentTraCuu;
                            return true;
                        }
                        case R.id.nav_luyendoc:{
                            fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentLuyenDoc).commit();
                            fragmentActive = fragmentLuyenDoc;
                            return true;
                        }
                        case R.id.nav_luyennghe:{
                            fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentLuyenNghe).commit();
                            fragmentActive = fragmentLuyenNghe;
                            return true;
                        }
                    }
                    return true;
                }
            };
}







