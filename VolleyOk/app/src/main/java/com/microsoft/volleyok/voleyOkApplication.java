package com.microsoft.volleyok;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by abmitra on 8/5/2015.
 */
public class voleyOkApplication extends Application {



    public voleyOkApplication() {
        super();



    }
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
