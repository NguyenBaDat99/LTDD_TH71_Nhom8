package com.example.dictionary;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DictionaryRequestTraCauYoutube extends AsyncTask<String, Integer, String>{

    WebView mWebView;
    int width;
    String keyWord;

    public DictionaryRequestTraCauYoutube(WebView webView, String keyWord , int width){
        mWebView = webView;
        this.width = width - 30;
        this.keyWord = keyWord;
    }

    @Override
    protected String doInBackground(String... params) {

        // replace with your own app id and app key
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        String def;
        try {
            JSONObject js = new JSONObject(result);
            JSONArray transcripts = js.getJSONArray("transcripts");
            String youtube_frame = "";

            for (int i = 0; i <= 10; i++) {
                JSONObject trans = transcripts.getJSONObject(i);
                JSONObject fields = trans.getJSONObject("fields");
                String youtube_id = fields.getString("youtube_id");
                int start = (int) Double.parseDouble(fields.getString("start"));

                youtube_frame += "<br><iframe width=\"" + width + "\" height=\"250\" src=\"https://www.youtube.com/embed/" + youtube_id + "?start=" + start + "\" frameborder=\"0\" allowfullscreen></iframe><br>";
            }

            String videoStr = "<html><body>" + youtube_frame + "<br>\n" +
                    "<script id=\"lbdict_plugin_frame\" type=\"text/javascript\">!function(){var h={s:\"https://dict.laban.vn\",w:380,h:450,hl:2,th:3};function loadScript(t,e){var n=document.createElement(\"script\");n.type=\"text/javascript\",n.readyState?n.onreadystatechange=function(){(\"loaded\"===n.readyState||\"complete\"===n.readyState)&&(n.onreadystatechange=null,e())}:n.onload=function(){e()},n.src=t,q=document.getElementById(\"lbdict_plugin_frame\"),q.parentNode.insertBefore(n,q)}setTimeout(function(){loadScript(\"https://stc-laban.zdn.vn/dictionary/js/plugin/lbdictplugin.frame.min.js\",function(){lbDictPluginFrame.init(h)})},1e3); }();</script></body></html>";

            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            WebSettings ws = mWebView.getSettings();
            ws.setJavaScriptEnabled(true);
            mWebView.loadData(videoStr, "text/html", "utf-8");

        } catch (JSONException e) {
            try{
                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                WebSettings ws = mWebView.getSettings();
                ws.setJavaScriptEnabled(true);
                mWebView.loadUrl("https://www." + keyWord);
                e.printStackTrace();
            }catch (Exception ex){

            }

        }

        Log.v("Result of Dictionary", "onPostExecute " + result);
    }

}
