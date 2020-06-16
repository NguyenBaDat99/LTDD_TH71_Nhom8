package com.example.dictionary.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.dictionary.DictionaryRequest.DictionaryRequestTraCauYoutube;
import com.example.dictionary.R;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class LuyenNgheFragment extends Fragment {
    @Nullable

    int width = 0;

    WebView mWebView;
    SearchView searchView;

    String keyWord;

    String url_api = "";

    LinearLayout mLinearLayout_introBtn;

    Button btnTed;
    Button btnBBC;
    Button btnYoutube;

    Button btnBack;
    Button btnHome;

    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyen_nghe, container, false);

        mWebView = view.findViewById(R.id.webView_YoutubeResult);
        searchView = view.findViewById(R.id.searchView_LuyenNghe);
        btnTed = view.findViewById(R.id.btnTed);
        btnBBC = view.findViewById(R.id.btnBBC);
        btnYoutube = view.findViewById(R.id.btnYoutube);
        btnBack = view.findViewById(R.id.btn_Back);
        btnHome = view.findViewById(R.id.btn_Home);

        btnTed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage("ted.com");
                mLinearLayout_introBtn.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }
        });
        btnBBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage("youtube.com/user/bbclearningenglish");
                mLinearLayout_introBtn.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }
        });
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage("youtube.com");
                mLinearLayout_introBtn.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWebView.goBack();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.setVisibility(View.GONE);
                mLinearLayout_introBtn.setVisibility(View.VISIBLE);
                mWebView.loadUrl("about:blank");
                mWebView.clearHistory();
            }
        });



        mLinearLayout_introBtn = view.findViewById(R.id.linearLayout_introButton);

        mWebView.setVisibility(View.GONE);

        width = (int) getActivity().getApplicationContext().getResources().getDisplayMetrics().xdpi;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keyWord = searchView.getQuery().toString();

                mLinearLayout_introBtn.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);

                DictionaryRequestTraCauYoutube dR = new DictionaryRequestTraCauYoutube(mWebView, keyWord ,width);
                url_api = dictionaryEntriesTraCau();
                dR.execute(url_api);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }

    private String dictionaryEntriesTraCau() {
        final String word = keyWord;
        final String word_id = word.toLowerCase();
        return "https://api.tracau.vn/WBBcwnwQpV89/trans/" + word_id;
    }

    public void goToPage(String URL){
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www." + URL);
    }


}
