package com.example.sashopc.delcandroidtest.async;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class TypeTestsAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TYPE_TESTS_URL = ApiConstants.URL + "TypeTests";
    String content;

    private ApiCallback apiCallback;

    public TypeTestsAsyncTask(ApiCallback apiCallback){
        this.apiCallback = apiCallback;

    }
    @Override
    protected String doInBackground(Void... params) {
        URL url;
        HttpURLConnection urlConnection;
        BufferedReader br;
        try{
            url = new URL(TYPE_TESTS_URL );

            urlConnection = (HttpURLConnection) url.openConnection();

            br = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null){
                sb.append(line);
                line = br.readLine();
            }
            content = sb.toString();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
        protected void onPostExecute(String response) {
            Log.d("asd", "content: " + content);
         apiCallback.onResponse(response);
        }
    }
