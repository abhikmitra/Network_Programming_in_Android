package com.microsoft.volleyok;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    int count = 10;
    public MainActivityFragment() {
    }
    private RequestQueue mQueue;
    TimeKeeper timeKeeper = new TimeKeeper("okhttp and volley demo");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Uri.Builder builder = new Uri.Builder();
        timeKeeper.start();
        String urlStr = "raw.githubusercontent.com";
        builder
                .scheme("https")
                .authority(urlStr)
                .appendPath("abhikmitra")
                .appendPath("Network_Programming_in_Android")
                .appendPath("master")
                .appendPath("cuisine.json");
        super.onStart();
        String url =builder.build().toString();
        mQueue = CustomVolleyRequestQueue.getInstance(getActivity().getApplicationContext())
                .getRequestQueue();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, builder.build().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Type collectionType = new TypeToken<List<Cuisine>>() {
                        }.getType();
                        ArrayList<Cuisine> pages = (ArrayList<Cuisine>) gson.fromJson(response, collectionType);
                        count--;
                        if(count==0){
                            timeKeeper.end();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
// Add the request to the RequestQueue.
        int times = count;
        voleyOkApplication v = (voleyOkApplication)getActivity().getApplicationContext();
        for(int i =0;i<times;i++) {
            mQueue.add(stringRequest);
        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
