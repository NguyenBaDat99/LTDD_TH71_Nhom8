package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.dictionary.ChangeTheme.AppPreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    private Switch mSwitch;
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
        setContentView(R.layout.activity_settings);


        mSwitch = findViewById(R.id.switch_changemode);
        if (preferenceManager.getDarkModeState() == true) {
            mSwitch.setChecked(true);
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (preferenceManager.getDarkModeState()) {
                    darkMode(false);
                } else {
                    darkMode(true);
                }
            }
        });
    }


    private void darkMode (boolean b) {
        preferenceManager.setDarkModeState(b);

        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
