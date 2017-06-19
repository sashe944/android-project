package com.example.sashopc.delcandroidtest.async;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginAsyncTask extends AsyncTask<String, Void, String> {

    private static final String LOGIN_URL = ApiConstants.URL + "HelloWorld?facNum=";

    private ApiCallback apiCallback;

    public LoginAsyncTask (ApiCallback apiCallback){
        this.apiCallback = apiCallback;
    }
    
    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection;
        BufferedReader br;
        try{
            url = new URL(LOGIN_URL + params[0] );

            urlConnection = (HttpURLConnection) url.openConnection();

            br = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null){
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
        protected void onPostExecute(String response) {
            Log.d("asd", "content: " + response);
            apiCallback.onResponse(response);
        }
    }
