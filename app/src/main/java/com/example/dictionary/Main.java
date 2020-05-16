package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dictionary.fragment.LuyenDocFragment;
import com.example.dictionary.fragment.LuyenNgheFragment;
import com.example.dictionary.fragment.TraCuuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Main extends AppCompatActivity {

    String url;

    Toolbar toolbar;
    SearchView searchView;
    AppCompatSpinner spinnerTraCuu;

    int cachTraCuu = 0;
    String tuCanTra = "";
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerTraCuu = findViewById(R.id.spinner_tracuu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_translate, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTraCuu.setAdapter(adapter);
        spinnerTraCuu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:{
                        cachTraCuu = 0;
                        traTu(cachTraCuu);
                    }
                    break;
                    case 1:{
                        cachTraCuu = 1;
                        traTu(cachTraCuu);
                    }
                    break;
                    case 2:{
                        traTu(cachTraCuu);
                    }
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tuCanTra  = searchView.getQuery().toString();

                FragmentManager fragmentManager = getSupportFragmentManager();
                TraCuuFragment traCuuFragment = (TraCuuFragment) fragmentManager.findFragmentById(R.id.fragmentTraCuu);

                txtKetQua = traCuuFragment.txtKetQua;
                traTu(cachTraCuu);
                traCuuFragment.txtKetQua = txtKetQua;

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //bottom_Navigation_Menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTraCuu,
                new TraCuuFragment()).commit();
    }

    private void traTu(int cachTraCuu) {
        switch (cachTraCuu)
        {
            case 0: {
                DictionaryRequestTraCau dR = new DictionaryRequestTraCau(this, txtKetQua);
                url = dictionaryEntriesTraCau();
                dR.execute(url);
            }break;
            case 1: {
                DictionaryRequest dR = new DictionaryRequest(this, txtKetQua);
                url = dictionaryEntries();
                dR.execute(url);
            }break;
        }
    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = tuCanTra;
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
    private String dictionaryEntriesTraCau() {
        final String word = tuCanTra;
        final String word_id = word.toLowerCase();
        return "https://api.tracau.vn/WBBcwnwQpV89/s/" + word_id +"/en";
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
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_tracuu:
                            selectedFragment = new TraCuuFragment();
                            break;
                        case R.id.nav_luyendoc:
                            selectedFragment = new LuyenDocFragment();
                            break;
                        case R.id.nav_luyennghe:
                            selectedFragment = new LuyenNgheFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTraCuu,
                            selectedFragment).commit();

                    return true;
                }
            };
}







