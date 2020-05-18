package com.example.dictionary;

import android.content.Context;
import android.media.AudioManager;
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


public class DictionaryRequest extends AsyncTask<String, Integer, String> {

    TextView showDef;
    MediaPlayer mediaPlayer;

    public DictionaryRequest(TextView tV, MediaPlayer mediaPlayer){
        showDef = tV;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected String doInBackground(String... params) {

        // replace with your own app id and app key
        final String app_id = "fd92b232";
        final String app_key = "807a29710154fbacd4c74a3db3921ca4";
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
            JSONArray e = entries.getJSONArray("entries");

            JSONObject pronunciation = laArray.getJSONObject(0);
            JSONArray pro = pronunciation.getJSONArray("pronunciations");

            JSONObject proSpell = pro.getJSONObject(0);
            String spell = proSpell.getString("phoneticSpelling");
            String urlAudioFile = proSpell.getString("audioFile");

            mediaPlayer.setDataSource(urlAudioFile);
            mediaPlayer.prepare();

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject deOJ = sensesArray.getJSONObject(0);
            JSONArray de = deOJ.getJSONArray("definitions");
            String def = de.getString(0);

            JSONArray ex = deOJ.getJSONArray("examples");
            JSONObject example1 = ex.getJSONObject(0);

            String ex1 = example1.getString("text");
            showDef.setText("● Phát âm: " + spell +"\n" +
                            "● Định nghĩa: \n - " + def +"\n"+
                            "● Ví dụ: \n - " + ex1 + "\n"
                            );
//            showDef.setText("Definitions: " + def);
//            ketQuaDich = def;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        Log.v("Result of Dictionary", "onPostExecute " + result);
    }


}

