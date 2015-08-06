package com.microsoft.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private final OkHttpClient client = new OkHttpClient();
    String TAG = "okHttpDemo";
    int count = 10;

    public MainActivityFragment() {
    }

    TimeKeeper timeKeeper = new TimeKeeper("okhttp demo");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Uri.Builder builder = new Uri.Builder();
        timeKeeper.start();
        String urlStr = "raw.githubusercontent.com";
        client.networkInterceptors().add(new StethoInterceptor());
        builder
                .scheme("https")
                .authority(urlStr)
                .appendPath("abhikmitra")
                .appendPath("Network_Programming_in_Android")
                .appendPath("master")
                .appendPath("cuisine.json");
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().build())
                .url(builder.build().toString())
                .build();
        int times = count;
        for(int i =0;i<times;i++) {
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Failed to execute " + request, e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<List<Cuisine>>() {
                    }.getType();
                    ArrayList<Cuisine> pages = (ArrayList<Cuisine>) gson.fromJson(response.body().string(), collectionType);
                    count--;
                    if(count==0){
                        timeKeeper.end();
                    }
                }
            });
        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
