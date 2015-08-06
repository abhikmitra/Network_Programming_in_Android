package com.microsoft.retrofitdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    TimeKeeper timeKeeper;
    public MainActivityFragment() {
        timeKeeper = new TimeKeeper("Retrofit ");
    }
    int count = 10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int times = count;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://raw.githubusercontent.com")
                .build();

        ApiService service = restAdapter.create(ApiService.class);
        timeKeeper.start();
        for(int i =0;i<times;i++) {
            service.getCuisines(new Callback<List<Cuisine>>() {
                @Override
                public void success(List<Cuisine> cuisines, Response response) {
                    count--;
                    if (count == 0) {
                        timeKeeper.end();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.v("Tag",error.toString());
                }
            });
        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
