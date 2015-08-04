package com.microsoft.demohttpurlconnection;

import android.util.Log;

/**
 * Created by abmitra on 8/4/2015.
 */
public class TimeKeeper {
    String TAG = "TimeKeeper";
    String name ;
    long start ;
    long stop ;
    public TimeKeeper(String name){
        this.name = name;
    }
    public void start(){
        start = System.currentTimeMillis();
        Log.d(TAG,this.name+ " operation starting at " + start);
    }
    public String end(){
        stop = System.currentTimeMillis();
        Log.d(TAG, this.name + " operation stopping at " + stop);
        Log.e(TAG,this.name +" operation ran for " + (stop - start) + "mili seconds");
        return this.name +" operation ran for " + (stop - start) + "mili seconds";
    }
}
