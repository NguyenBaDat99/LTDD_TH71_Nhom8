package com.example.dictionary.fragment;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
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

    RadioButton rbAnhViet, rbAnhAnh, rbVietAnh;

    RadioGroup radioCachTraCuu;
    SearchView searchView;
    String url; //Đường dẫn cho api
    Button btnPlay;

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tra_cuu, container, false);

        txtKetQua = view.findViewById(R.id.txtKetQua);

        rbAnhViet = view.findViewById(R.id.radio_av);
        rbAnhAnh = view.findViewById(R.id.radio_aa);
        rbVietAnh = view.findViewById(R.id.radio_va);

        radioCachTraCuu = view .findViewById(R.id.radio_dich);
        radioCachTraCuu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
//                tuCanTra  = searchView.getQuery().toString();
                switch(checkedId) {

                    case R.id.radio_av:{
                        rbAnhViet.setTextColor(Color.rgb(98,0,238));
                        rbAnhAnh.setTextColor(Color.WHITE);
                        rbVietAnh.setTextColor(Color.WHITE);

                        cachTraCuu = 0;
                        traTu(cachTraCuu);

                    }
                        break;
                    case R.id.radio_aa:{
                        rbAnhAnh.setTextColor(Color.rgb(98,0,238));
                        rbAnhViet.setTextColor(Color.WHITE);
                        rbVietAnh.setTextColor(Color.WHITE);

                        cachTraCuu = 1;
                        traTu(cachTraCuu);
                    }
                        break;
                    case R.id.radio_va:{
                        rbVietAnh.setTextColor(Color.rgb(98,0,238));
                        rbAnhAnh.setTextColor(Color.WHITE);
                        rbAnhViet.setTextColor(Color.WHITE);

                        traTu(cachTraCuu);
                    }break;
                }
            }
        });

        searchView = view.findViewById(R.id.searchView_TraCuu);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                traTu(cachTraCuu);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnPlay = view.findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });



        return view;
    }

    //Hàm tra từ cần dịch và xuất kết quả dịch
    private void traTu(int cachTraCuu) {
        tuCanTra  = searchView.getQuery().toString();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        switch (cachTraCuu)
        {
            case 0: {
                DictionaryRequestTraCau dR = new DictionaryRequestTraCau(txtKetQua);
                url = dictionaryEntriesTraCau();
                dR.execute(url);
            }break;
            case 1: {
                DictionaryRequest dR = new DictionaryRequest(txtKetQua, mediaPlayer);
                url = dictionaryEntries();
                dR.execute(url);
            }break;
        }
    }

    //Tạo đường dẫn liên kết api Oxford Dictionary (Anh-Anh)
    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = tuCanTra;
        final String fields = "";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "&strictMatch=" + strictMatch;
    }
    //Tạo đường dẫn liên kết api TraCau (Anh-Việt)
    private String dictionaryEntriesTraCau() {
        final String word = tuCanTra;
        final String word_id = word.toLowerCase();
        return "https://api.tracau.vn/WBBcwnwQpV89/s/" + word_id +"/en";
    }

}
