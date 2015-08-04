package com.microsoft.demohttpurlconnection;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abmitra on 8/4/2015.
 */
public class demoAsyncTask extends AsyncTask<String,Integer,String> {
    String TAG  = "demoAsyncTask";
    TimeKeeper timeKeeper = new TimeKeeper("Fetching Cuisines");
    Feedback feedback;
    public demoAsyncTask(Feedback feedback) {
        super();
        this.feedback = feedback;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        timeKeeper.start();
        String urlStr = params[0];
        Uri.Builder builder = new Uri.Builder();
        builder
                .scheme("https")
                .authority(urlStr)
                .appendPath("abhikmitra")
                .appendPath("Network_Programming_in_Android")
                .appendPath("master")
                .appendPath("cuisine.json");

        String jsonStr = getDataFromServer(builder.build());
        return jsonStr;
    }

    @Override
    protected void onPostExecute(String jsonStr) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Cuisine>>() {
        }.getType();
        ArrayList<Cuisine> pages = (ArrayList<Cuisine>) gson.fromJson(jsonStr, collectionType);
        String duration = timeKeeper.end();
        super.onPostExecute(jsonStr);
        feedback.onTaskFinish(pages);
    }

    protected String getDataFromServer(Uri uri){
        HttpURLConnection urlConnection = null;
        String result = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream==null){
                result = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line=reader.readLine())!=null){
                buffer.append(line + "\n");
            }

            if(buffer.length() == 0){
                result = null;
            }

            result = buffer.toString();

        } catch (MalformedURLException e){
            Log.e(TAG, "Url is bad", e);

        }catch (IOException e){
            Log.e(TAG, "IO Exception", e);

        }catch (Exception e){
            Log.e(TAG, "Something went wrong", e);
        }
        finally {
            closeConnections(urlConnection,reader);
        }
        return result;
    }
    protected void closeConnections(HttpURLConnection urlConnection, BufferedReader reader){
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (final IOException e) {
                Log.e(TAG, "Error closing stream", e);
            }
        }
    }
    interface Feedback{
        void onTaskFinish(List<Cuisine> str);
    }
}
