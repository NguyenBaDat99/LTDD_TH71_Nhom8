package com.example.dictionary;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dictionary.fragment.LuyenDocFragment;
import com.example.dictionary.fragment.LuyenNgheFragment;
import com.example.dictionary.fragment.TraCuuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    Toolbar toolbar;

//    Fragment mLuyenNgheFragment = new LuyenNgheFragment();
//
//
//    Bundle savedState;
//
//    int fragmentActive = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        savedState = savedInstanceState;

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

//        if(savedInstanceState != null){
//            mLuyenNgheFragment = getSupportFragmentManager().getFragment(savedState, "fLuyenNghe");
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
//                    mLuyenNgheFragment).commit();
//        }
//        else{
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragmentHienThi, new TraCuuFragment());
//            fragmentTransaction.commit();
//        }

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
//                            fragmentActive = 1;
                        }break;
                        case R.id.nav_luyendoc:{
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
                                    new LuyenDocFragment()).commit();
//                            fragmentActive = 2;
                        }break;
                        case R.id.nav_luyennghe:{
//                            if(savedState != null){
//                                mLuyenNgheFragment = getSupportFragmentManager().getFragment(savedState, "fLuyenNghe");
//                                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
//                                        mLuyenNgheFragment).commit();
//                            }else{
//                                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
//                                        new LuyenNgheFragment()).commit();
//                            }
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHienThi,
                                    new LuyenNgheFragment()).commit();
//                            fragmentActive = 3;

                        }break;
                    }
                    return true;
                }
            };

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        //Save the fragment's instance
//        if(fragmentActive == 3){
//            getSupportFragmentManager().putFragment(outState, "fLuyenNghe", mLuyenNgheFragment);
//        }
//    }
}







