package com.example.dictionary.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dictionary.R;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class LuyenNgheFragment extends Fragment {
    @Nullable

    WebView mWebView;

    int state = 0;
    String urlNow = null;

    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyen_nghe, container, false);

        mWebView = view.findViewById(R.id.webView_YoutubeResult);

//        if (savedInstanceState != null) {
//            mWebView.restoreState(savedInstanceState);
//        } else {
//            int width = (int) getActivity().getApplicationContext().getResources().getDisplayMetrics().xdpi;
//            int height = getActivity().getApplicationContext().getResources().getDisplayMetrics().heightPixels;
//
//            //TODO: Trang Luyện nghe
//
//            String videoStr = "<html><body>" +
//                    "<iframe width=\"" + width + "\" height=\"250\" src=\"https://www.youtube.com/embed/lTRiuFIWV54?start=320\" frameborder=\"0\" allowfullscreen></iframe>" +
//                    "<iframe width=\"" + width + "\" height=\"200\" src=\"https://www.youtube.com/embed/jO2viLEW-1A\" frameborder=\"0\" allowfullscreen></iframe>" +
//                    "<iframe width=\"" + width + "\" height=\"200\" src=\"https://www.youtube.com/embed/kOCkne-Bku4\" frameborder=\"0\" allowfullscreen></iframe>" +
//                    "</body></html>";
//
//            mWebView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    return false;
//                }
//            });
//            WebSettings ws = mWebView.getSettings();
//            ws.setJavaScriptEnabled(true);
//            mWebView.loadData(videoStr, "text/html", "utf-8");
//        }

        if(state == 0){
            int width = (int) getActivity().getApplicationContext().getResources().getDisplayMetrics().xdpi;
            int height = getActivity().getApplicationContext().getResources().getDisplayMetrics().heightPixels;

            //TODO: Trang Luyện nghe

            String videoStr = "<html><body>" +
                    "<iframe width=\"" + width + "\" height=\"250\" src=\"https://www.youtube.com/embed/lTRiuFIWV54?start=320\" frameborder=\"0\" allowfullscreen></iframe>" +
                    "<iframe width=\"" + width + "\" height=\"200\" src=\"https://www.youtube.com/embed/jO2viLEW-1A\" frameborder=\"0\" allowfullscreen></iframe>" +
                    "<iframe width=\"" + width + "\" height=\"200\" src=\"https://www.youtube.com/embed/kOCkne-Bku4\" frameborder=\"0\" allowfullscreen></iframe>" +
                    "</body></html>";

            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            WebSettings ws = mWebView.getSettings();
            ws.setJavaScriptEnabled(true);
            mWebView.loadData(videoStr, "text/html", "utf-8");
            urlNow = mWebView.getUrl();
            state = 1;
        }
        mWebView.loadUrl(urlNow);


        return view;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (savedInstanceState != null) {
//            mWebView.restoreState(savedInstanceState);
//        } else {
//            int width = (int) getActivity().getApplicationContext().getResources().getDisplayMetrics().xdpi;
//            int height = getActivity().getApplicationContext().getResources().getDisplayMetrics().heightPixels;
//
//            //TODO: Trang Luyện nghe
//
//            String videoStr = "<html><body>" +
//                    "<iframe width=\"" + width + "\" height=\"250\" src=\"https://www.youtube.com/embed/lTRiuFIWV54?start=320\" frameborder=\"0\" allowfullscreen></iframe>" +
//                    "<iframe width=\"" + width + "\" height=\"200\" src=\"https://www.youtube.com/embed/jO2viLEW-1A\" frameborder=\"0\" allowfullscreen></iframe>" +
//                    "<iframe width=\"" + width + "\" height=\"200\" src=\"https://www.youtube.com/embed/kOCkne-Bku4\" frameborder=\"0\" allowfullscreen></iframe>" +
//                    "</body></html>";
//
//            mWebView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    return false;
//                }
//            });
//            WebSettings ws = mWebView.getSettings();
//            ws.setJavaScriptEnabled(true);
//            mWebView.loadData(videoStr, "text/html", "utf-8");
//        }
//    }

//    @Override
//    public void onSaveInstanceState(Bundle outState){
//        mWebView.saveState(outState);
//        super.onSaveInstanceState(outState);
//    }

    @Override
    public void onResume(){
        mWebView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mWebView.onPause();

        state = 1;
        super.onPause();
    }

}
