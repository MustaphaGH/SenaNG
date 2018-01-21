package com.sena;

import android.app.Application;

/**
 * Created by mustapha on 21/01/18.
 */

public class MainApplication extends Application {

    private static Application instance;

    public static Application getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
