package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.dictionary.ChangeTheme.AppPreferenceManager;

public class AboutActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_about);


    }
}
