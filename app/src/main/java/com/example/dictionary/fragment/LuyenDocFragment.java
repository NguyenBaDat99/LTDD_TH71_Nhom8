package com.example.dictionary.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.dictionary.Adapter.ItemAdapter;
import com.example.dictionary.Adapter.ListViewAdapter;
import com.example.dictionary.DictionaryRequest.BaiDoc;
import com.example.dictionary.DictionaryRequest.ListItem;
import com.example.dictionary.DictionaryRequest.QLBaiDoc;
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
    QLBaiDoc ql = new QLBaiDoc();

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
        //TODO: Trang Luyện đọc
        listView = view.findViewById(R.id.ListView);
        search = view.findViewById(R.id.SearchView);


        //Add data
//        String a = "N-ILS4-1";
//        String b = "Bài nghe 1 IELTS 4.0";
//        String c = "Man: Hello, Tom Wilson’s, can I help you?\n" +
//                "Woman: Hi there, it’s Emma Lloyd here. I’m calling about my motorbike. Is it ready to be collected yet?\n" +
//                "Man: Can you remind me, what’s the make of the bike?\n" +
//                "Woman: It’s a City Zip. It’s blue.\n" +
//                "Man: Oh yes. We had to order in some parts, but they still haven’t arrived yet I’m afraid.\n" +
//                "Woman: Do you know when the bike will be ready to pick up?\n" +
//                "Man: Sorry, I don’t know. But I’ll call our suppliers and find out if they’ve sent out the spare parts yet. Once the parts are here we can fix the bike in two or three days.\n" +
//                "Woman: Okay, I’ll give you a call at the end of the week, then.\n" +
//                "Man: Sure.";
//        String d = "N-ILS4";
//        String e = "IELTS 4.0";
//        BaiDoc bt = new BaiDoc(e,d, a, c, b);
//        myRef.child("BaiDoc").push().setValue(bt);

//


        myRef.child("BaiDoc").addChildEventListener(new ChildEventListener() {
            int i = 0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BaiDoc bt = dataSnapshot.getValue(BaiDoc.class);
                title.add(bt.TenBaiTap);
                subtitle.add(bt.CapBac);
                img.add(R.drawable.scroll);

                ListItem listItem = new ListItem(bt.TenBaiTap, bt.CapBac, R.drawable.scroll, bt.NoiDung);
                arrayList.add(listItem);


                if(i == 11)
                {
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
                            if(TextUtils.isEmpty(s)){
                                listView.clearTextFilter();
                                LAdapter.filter("");
                            }
                            else
                                LAdapter.filter(s);
                            return true;
                        }
                    });
                }
//                Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                i += 1;
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



