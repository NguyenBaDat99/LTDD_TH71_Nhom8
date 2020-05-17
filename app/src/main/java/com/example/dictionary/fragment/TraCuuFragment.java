package com.example.dictionary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.dictionary.DictionaryRequest;
import com.example.dictionary.DictionaryRequestTraCau;
import com.example.dictionary.R;


public class TraCuuFragment extends Fragment {

    public TextView txtKetQua; //TextView dùng để xuất kết quả từ cần tra
    int cachTraCuu = 0;  //Cách để tra cứu (0 = Anh-Việt; 1 = Anh-Anh; 2 = Việt-Anh)
    String tuCanTra = ""; //Từ cần tra

    RadioGroup radioCachTraCuu;
    SearchView searchView;
    String url; //Đường dẫn cho api

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tra_cuu, container, false);

        txtKetQua = view.findViewById(R.id.txtKetQua);

        radioCachTraCuu = view .findViewById(R.id.radio_dich);
        radioCachTraCuu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                tuCanTra  = searchView.getQuery().toString();
                switch(checkedId) {

                    case R.id.radio_av:{
                        cachTraCuu = 0;
                        traTu(cachTraCuu);
                    }
                        break;
                    case R.id.radio_aa:{
                        cachTraCuu = 1;
                        traTu(cachTraCuu);
                    }
                        break;
                    case R.id.radio_va:{
                        traTu(cachTraCuu);
                    }break;
                }
            }
        });

        searchView = view.findViewById(R.id.searchView_TraCuu);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tuCanTra  = searchView.getQuery().toString();

                traTu(cachTraCuu);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    //Hàm tra từ cần dịch và xuất kết quả dịch
    private void traTu(int cachTraCuu) {
        switch (cachTraCuu)
        {
            case 0: {
                DictionaryRequestTraCau dR = new DictionaryRequestTraCau(txtKetQua);
                url = dictionaryEntriesTraCau();
                dR.execute(url);
                String a = "";
            }break;
            case 1: {
                DictionaryRequest dR = new DictionaryRequest(txtKetQua);
                url = dictionaryEntries();
                dR.execute(url);
            }break;
        }
    }

    //Tạo đường dẫn liên kết api Oxford Dictionary (Anh-Anh)
    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = tuCanTra;
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
    //Tạo đường dẫn liên kết api TraCau (Anh-Việt)
    private String dictionaryEntriesTraCau() {
        final String word = tuCanTra;
        final String word_id = word.toLowerCase();
        return "https://api.tracau.vn/WBBcwnwQpV89/s/" + word_id +"/en";
    }

}
