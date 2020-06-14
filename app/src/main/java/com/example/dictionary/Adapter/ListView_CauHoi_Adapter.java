//package com.example.dictionary.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.dictionary.DictionaryRequest.ListItem_CauHoi;
//import com.example.dictionary.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListView_CauHoi_Adapter extends BaseAdapter {
//
//    Context mContext;
//    LayoutInflater inflater;
//    List<ListItem_CauHoi> ItemList;
//    ArrayList<ListItem_CauHoi> arrayList;
//
//    public ListView_CauHoi_Adapter(Context context, List<ListItem_CauHoi> itemList) {
//        mContext = context;
//        this.ItemList = itemList;
//        this.inflater = LayoutInflater.from(mContext);
//        this.arrayList = new ArrayList<ListItem_CauHoi>();
//        this.arrayList.addAll(ItemList);
//    }
//
//    @Override
//    public int getCount() {
//        return ItemList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return ItemList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        ViewCauHoiHolder holder;
//        if (view == null)
//        {
//            holder = new ViewCauHoiHolder();
//            view = inflater.inflate(R.layout.item, null);
//
//            //Định vị views trong item.xml
//            holder.mTitleTv = view.findViewById(R.id.textViewContent);
//            holder.mDescTv = view.findViewById(R.id.textViewQuestion);
//            holder.mIconIv = view.findViewById(R.id.radioGroup);
//
//            view.setTag(holder);
//        }
//        else holder = (ViewCauHoiHolder) view.getTag();
//        //Gán kết quả vào textview
//        holder.mTitleTv.setText(ItemList.get(position).getTitle());
//        holder.mDescTv.setText(ItemList.get(position).getSubtitle());
//        //Gán kết quả vào img
//        holder.mIconIv.setImageResource(ItemList.get(position).getImg());
//
//        //Gắn sự kiện onClick vào các item
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        return view;
//    }
//
//
//    public View getViewCauHoi(int position, View view, ViewGroup parent) {
//        ViewHolder holder;
//        if (view == null)
//        {
//            holder = new ViewHolder();
//            view = inflater.inflate(R.layout.item, null);
//
//            //Định vị views trong item.xml
//            holder.mTitleTv = view.findViewById(R.id.title);
//            holder.mDescTv = view.findViewById(R.id.subtitle);
//            holder.mIconIv = view.findViewById(R.id.img);
//
//            view.setTag(holder);
//        }
//        else holder = (ViewHolder) view.getTag();
//        //Gán kết quả vào textview
//        holder.mTitleTv.setText(ItemList.get(position).getTitle());
//        holder.mDescTv.setText(ItemList.get(position).getSubtitle());
//        //Gán kết quả vào img
//        holder.mIconIv.setImageResource(ItemList.get(position).getImg());
//
//        //Gắn sự kiện onClick vào các item
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        return view;
//    }
//
//
//
//    public void filter (String charText){
//        charText = charText.toLowerCase();
//        ItemList.clear();
//        if(charText.length() == 0){
//            ItemList.addAll(arrayList);
//        }
//        else {
//            for(ListItem_CauHoi item : arrayList){
//                if (item.getTitle().toLowerCase().contains(charText)){
//                    ItemList.add(item);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
//}
//
//
//
//class ViewCauHoiHolder{
//    TextView mContent, mQuestion, mAnswer;
//}