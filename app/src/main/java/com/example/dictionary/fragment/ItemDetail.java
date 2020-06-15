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

import com.example.dictionary.R;

public class ItemDetail extends AppCompatActivity {

    MenuItem back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        back = findViewById(R.id.HomeMenu);
        setContentView(R.layout.activity_item_detail);
        ActionBar actionBar = getSupportActionBar();
        TextView title = findViewById(R.id.childTitle);
        TextView content = findViewById(R.id.childContent);

        Intent intent = getIntent();
        String parentTitle = intent.getStringExtra("Title");
        String parentContent = intent.getStringExtra("Content");

        actionBar.setTitle(parentTitle);
        title.setText(parentTitle);
        content.setText(parentContent);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.back, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}