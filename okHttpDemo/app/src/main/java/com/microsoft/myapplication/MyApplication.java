package com.microsoft.myapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by abmitra on 8/6/2015.
 */
public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
