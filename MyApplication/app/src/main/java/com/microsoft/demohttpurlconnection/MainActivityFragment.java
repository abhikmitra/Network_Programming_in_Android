package com.microsoft.demohttpurlconnection;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements demoAsyncTask.Feedback {

    public MainActivityFragment() {
    }
    TimeKeeper timeKeeper = new TimeKeeper("Fetching Cuisines from fragment");
    int count = 10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        timeKeeper.start();
        int times = count;
        for(int i =0;i<times;i++){
            new demoAsyncTask(this).execute("raw.githubusercontent.com");
        }
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onTaskFinish(List<Cuisine> str) {
        count--;
        if(count==0){
            timeKeeper.end();
        }

    }
}
