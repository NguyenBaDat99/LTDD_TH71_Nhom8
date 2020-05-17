package com.example.dictionary;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dictionary.fragment.LuyenDocFragment;
import com.example.dictionary.fragment.LuyenNgheFragment;
import com.example.dictionary.fragment.TraCuuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Main extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bottom_Navigation_Menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //Load fragment TraCuu
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHienThi, new TraCuuFragment());
        fragmentTransaction.commit();

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
                return true;
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
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
                                    new TraCuuFragment()).commit();
                        }break;
                        case R.id.nav_luyendoc:{
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
                                    new LuyenDocFragment()).commit();
                        }break;
                        case R.id.nav_luyennghe:{
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
                                    new LuyenNgheFragment()).commit();
                        }break;
                    }

                    return true;
                }
            };
}







