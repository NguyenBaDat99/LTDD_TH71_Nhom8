package com.example.dictionary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dictionary.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    private final ArrayList<Integer> img;


    public ItemAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle, ArrayList<Integer> img) {

        super(context, R.layout.item, maintitle);

        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.img = img;
    }


    @Override
    public View getView (int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item, null, true);
        TextView title = row.findViewById(R.id.title);
        TextView sub_title = row.findViewById(R.id.subtitle);
        ImageView image = row.findViewById(R.id.img);

        title.setText(maintitle.get(position));
        sub_title.setText(subtitle.get(position));
        image.setImageResource(img.get(position));
        return row;
    }
}
