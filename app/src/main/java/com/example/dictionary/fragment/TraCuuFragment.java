package com.example.dictionary.fragment;

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

import com.example.dictionary.DictionaryRequest.DictionaryRequestOxford;
import com.example.dictionary.DictionaryRequest.DictionaryRequestOxfordPhienAm;
import com.example.dictionary.DictionaryRequest.DictionaryRequestTraCauAnhViet;
import com.example.dictionary.DictionaryRequest.DictionaryRequestTraCauVietAnh;
import com.example.dictionary.R;


public class TraCuuFragment extends Fragment {

    public TextView txtTuVaPhienAm;
    public TextView txtKetQua; //TextView dùng để xuất kết quả từ cần tra
    int cachTraCuu = 0;  //Cách để tra cứu (0 = Anh-Việt; 1 = Anh-Anh; 2 = Việt-Anh)
    private String tuCanTra = ""; //Từ cần tra

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

        txtKetQua = view.findViewById(R.id.txt_KetQua);
        txtTuVaPhienAm = view.findViewById(R.id.txt_TuVaPhienAm);

        rbAnhViet = view.findViewById(R.id.radio_av);
        rbAnhAnh = view.findViewById(R.id.radio_aa);
        rbVietAnh = view.findViewById(R.id.radio_va);

        searchView = view.findViewById(R.id.searchView_TraCuu);

        radioCachTraCuu = view .findViewById(R.id.radio_dich);
        radioCachTraCuu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radio_av:{
                        if(cachTraCuu == 2){
                            searchView.setQuery("", false);
                            txtKetQua.setText("");
                            txtTuVaPhienAm.setText("");
                        }
                        cachTraCuu = 0;
                        traTu(cachTraCuu);
                        btnPlay.setVisibility(View.VISIBLE);
                    }
                        break;
                    case R.id.radio_aa:{
                        if(cachTraCuu == 2){
                            searchView.setQuery("", false);
                            txtKetQua.setText("");
                            txtTuVaPhienAm.setText("");
                        }
                        cachTraCuu = 1;
                        traTu(cachTraCuu);
                        btnPlay.setVisibility(View.VISIBLE);
                    }
                        break;
                    case R.id.radio_va:{
                        searchView.setQuery("", false);
                        txtKetQua.setText("");
                        txtTuVaPhienAm.setText("");
                        cachTraCuu = 2;
                        traTu(cachTraCuu);
                        btnPlay.setVisibility(View.GONE);
                    }break;
                }
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                traTu(cachTraCuu);
                txtTuVaPhienAm.setText(tuCanTra);

                if(cachTraCuu != 2)
                {
                    btnPlay.setVisibility(View.VISIBLE);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.reset();

                    DictionaryRequestOxfordPhienAm dR = new DictionaryRequestOxfordPhienAm(txtTuVaPhienAm, mediaPlayer);
                    url = dictionaryEntriesOxfordPhienAm();
                    dR.execute(url);
                }

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnPlay = view.findViewById(R.id.btn_PhatAm);
        btnPlay.setVisibility(View.GONE);
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

        switch (cachTraCuu)
        {
            case 0: {
                DictionaryRequestTraCauAnhViet dR = new DictionaryRequestTraCauAnhViet(txtKetQua);
                url = dictionaryEntriesTraCauAnhViet();
                dR.execute(url);
            }break;
            case 1: {
                DictionaryRequestOxford dR = new DictionaryRequestOxford(txtKetQua);
                url = dictionaryEntriesOxford();
                dR.execute(url);
            }break;
            case 2: {
                DictionaryRequestTraCauVietAnh dR = new DictionaryRequestTraCauVietAnh(txtKetQua);
                url = dictionaryEntriesTraCauVietAnh();
                dR.execute(url);
            }break;
        }

    }

    //Tạo đường dẫn liên kết api Oxford Dictionary (Anh-Anh)
    private String dictionaryEntriesOxford() {
        final String language = "en-gb";
        final String word = tuCanTra;
        final String fields = "";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "&strictMatch=" + strictMatch;
    }
    private String dictionaryEntriesOxfordPhienAm() {
        final String language = "en-gb";
        final String word = tuCanTra;
        final String fields = "pronunciations";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?fields=" + fields + "&strictMatch=" + strictMatch;
    }
    //Tạo đường dẫn liên kết api TraCau (Anh-Việt)
    private String dictionaryEntriesTraCauAnhViet() {
        final String word = tuCanTra;
        final String word_id = word.toLowerCase();
        return "https://api.tracau.vn/WBBcwnwQpV89/s/" + word_id +"/en";
    }
    private String dictionaryEntriesTraCauVietAnh() {
        final String word = tuCanTra;
        final String word_id = word.toLowerCase();
        return "https://api.tracau.vn/WBBcwnwQpV89/s/" + word_id +"/vi";
    }

}
