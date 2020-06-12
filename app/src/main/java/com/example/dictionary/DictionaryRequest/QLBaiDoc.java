package com.example.dictionary.DictionaryRequest;

import java.util.ArrayList;

public class QLBaiDoc {
    public ArrayList<BaiDoc> ql = new ArrayList<BaiDoc>();

    public void them(BaiDoc bt){
        this.ql.add(bt);
    }

    public int kichThuoc(){
        return ql.size();
    }
}
