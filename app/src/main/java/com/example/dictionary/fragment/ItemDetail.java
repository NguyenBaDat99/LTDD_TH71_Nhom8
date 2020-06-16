package com.example.dictionary.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dictionary.ChangeTheme.AppPreferenceManager;
import com.example.dictionary.R;

public class ItemDetail extends AppCompatActivity {

    AppPreferenceManager preferenceManager;

    MenuItem back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferenceManager = new AppPreferenceManager( this);
        if (preferenceManager.getDarkModeState()) {
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_detail);
        ActionBar actionBar = getSupportActionBar();
        TextView content = findViewById(R.id.childContent);

        Intent intent = getIntent();
        String parentTitle = intent.getStringExtra("Title");
        String parentContent = intent.getStringExtra("Content");

        actionBar.setTitle(parentTitle);
        content.setText(parentContent);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}