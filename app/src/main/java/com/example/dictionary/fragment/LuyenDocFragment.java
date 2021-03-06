package com.example.dictionary.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.dictionary.Adapter.ItemAdapter;
import com.example.dictionary.Adapter.ListViewAdapter;
import com.example.dictionary.DictionaryRequest.BaiDoc;
import com.example.dictionary.DictionaryRequest.ListItem;
import com.example.dictionary.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LuyenDocFragment extends Fragment {
    @Nullable

    //FireBase
            FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    //ListItem
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String>  subtitle = new ArrayList<>();
    ArrayList<Integer>  img = new ArrayList<>();
    ArrayList<ListItem> arrayList = new ArrayList<ListItem>();


    //Adapter
    ItemAdapter adapter;
    ListViewAdapter LAdapter;

    ListView listView;
    SearchView search;
    int i = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyen_doc, container, false);

        listView = view.findViewById(R.id.ListView);
        search = view.findViewById(R.id.SearchView);


        myRef.child("BaiDoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BaiDoc bt = dataSnapshot.getValue(BaiDoc.class);
                title.add(bt.TenBaiTap);
                subtitle.add(bt.CapBac);
                img.add(R.drawable.book);


                ListItem listItem = new ListItem(bt.TenBaiTap, bt.CapBac, R.drawable.book, bt.NoiDung);
                arrayList.add(listItem);

                    //apdater của thầy hiếu
//                    adapter = new ItemAdapter(getActivity(), title, subtitle, img);
//                    listView.setAdapter(adapter);
//                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            back.setEnabled(true);
//                            Toast.makeText(getContext(), subtitle.get(position), Toast.LENGTH_SHORT).show();
//                        }
//                    });

                    LAdapter = new ListViewAdapter(getContext(), arrayList);
                    //truyền adapter tới listview
                    listView.setAdapter(LAdapter);

                    search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            if (TextUtils.isEmpty(s)) {
                                listView.clearTextFilter();
                                LAdapter.filter("");
                            } else {
                                LAdapter.filter(s);
                            }
                            return true;
                        }
                    });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }
}



