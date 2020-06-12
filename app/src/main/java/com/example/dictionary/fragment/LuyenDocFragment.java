package com.example.dictionary.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dictionary.DictionaryRequest.BaiDoc;
import com.example.dictionary.DictionaryRequest.QLBaiDoc;
import com.example.dictionary.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LuyenDocFragment extends Fragment {
    @Nullable


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    ListView listView;
    QLBaiDoc ql = new QLBaiDoc();
    ArrayList<BaiDoc> b = new ArrayList<>();
    int i = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyen_doc, container, false);
        //TODO: Trang Luyện đọc
        listView = view.findViewById(R.id.ListView);
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



        myRef.child("BaiDoc").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BaiDoc bt = dataSnapshot.getValue(BaiDoc.class);

                b.add(bt);
                ql.them(bt);
                if(i == 10) {
                    listView.addChildrenForAccessibility(item);
                }
//                Toast.makeText(getContext(), String.valueOf(ql.ql.get(0).TenBaiTap), Toast.LENGTH_SHORT).show();
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

    public class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.item, null);

            ImageView img = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.textView);
            TextView textView1 = view.findViewById(R.id.textView2);

            textView.setText();
            textView1.setText();
            return null;
        }
    }
}



