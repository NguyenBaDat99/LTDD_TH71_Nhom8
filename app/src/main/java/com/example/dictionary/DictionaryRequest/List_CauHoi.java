package com.example.dictionary.DictionaryRequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dictionary.Adapter.ListViewAdapter;
import com.example.dictionary.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class List_CauHoi extends AppCompatActivity {

    //FireBase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    //ListItem
    ArrayList<String> content = new ArrayList<>();
    ArrayList<String>  question = new ArrayList<>();
    ArrayList<String>  answer = new ArrayList<>();
    ArrayList<ListItem> arrayList = new ArrayList<ListItem>();

    //Adapter
    ListViewAdapter LAdapter;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__cau_hoi);
    }
}
