package com.example.dictionary.DictionaryRequest;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DictionaryRequestOxfordPhienAm extends AsyncTask<String, Integer, String> {

    TextView showDef;
    MediaPlayer mediaPlayer;

    public DictionaryRequestOxfordPhienAm(TextView tV, MediaPlayer mediaPlayer){
        showDef = tV;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected String doInBackground(String... params) {

        // replace with your own app id and app key
        final String app_id = "9ec1a302";
        final String app_key = "9f2bfe3b032ed2b3fcd9dc7d8b227fd3";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

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

        try {
            JSONObject js = new JSONObject(result);
            JSONArray results = js.getJSONArray("results");

            JSONObject lEntries = results.getJSONObject(0);
            JSONArray laArray = lEntries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray en = entries.getJSONArray("entries");

            JSONObject pronunciation = en.getJSONObject(0);
            JSONArray pro = pronunciation.getJSONArray("pronunciations");

            //Lấy cách phát âm
            String spell = "";
            JSONObject proSpell = pro.getJSONObject(0);
            spell = " /" +  proSpell.getString("phoneticSpelling") + "/";

            //Lấy file audio phát âm
            String urlAudioFile = proSpell.getString("audioFile");
            mediaPlayer.setDataSource(urlAudioFile);
            mediaPlayer.prepare();

            showDef.append(spell);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Log.v("Result of Dictionary", "onPostExecute " + result);
    }


}

