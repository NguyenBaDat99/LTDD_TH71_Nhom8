package com.example.dictionary.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.MediaController;
import android.widget.VideoView;


public class LuyenNgheFragment extends Fragment {
    @Nullable

    WebView mWebView;

//    VideoView mVideoView;

    MediaPlayer mediaPlayer = new MediaPlayer();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyen_nghe, container, false);

        //TODO: Trang Luyện nghe

        mWebView = view.findViewById(R.id.videoview);
//        mVideoView = view.findViewById(R.id.videoView);

//        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + R.raw.perfect;
//        Uri uri = Uri.parse(videoPath);
//        mVideoView.setVideoURI(uri);

//        String uri = "https://www.youtube.com/watch?v=lTRiuFIWV54&t=12.14";
//        mVideoView.setVideoURI(Uri.parse(uri));

//        MediaController mediaController = new MediaController(getContext());
//        mVideoView.setMediaController(mediaController);
//        mediaController.setAnchorView(mVideoView);

        String videoStr = "<html><body>" +
                "<iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/lTRiuFIWV54?start=320\" frameborder=\"0\" allowfullscreen></iframe>" +
                "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/lTRiuFIWV54?start=389\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>" +
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

        return view;
    }
}
