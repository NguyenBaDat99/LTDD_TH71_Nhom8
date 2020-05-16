package com.example.dictionary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DictionaryRequestTraCau extends AsyncTask<String, Integer, String>{
    Context context;
    TextView showDef;

    DictionaryRequestTraCau(Context context, TextView tV){
        this.context = context;
        showDef = tV;
    }
    @Override
    protected String doInBackground(String... params) {

        //TODO: replace with your own app id and app key
//        final String app_id = "WBBcwnwQpV89";
//        final String app_key = "WBBcwnwQpV89";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
//            urlConnection.setRequestProperty("app_id",app_id);
//            urlConnection.setRequestProperty("app_key",app_key);

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
            JSONArray sentences = js.getJSONArray("sentences");

            JSONObject trans = sentences.getJSONObject(0);
            JSONObject d = trans.getJSONObject("fields");

            def = d.getString("vi");
            showDef.setText("Meaning: " + def);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("Result of Dictionary", "onPostExecute " + result);
    }
}
