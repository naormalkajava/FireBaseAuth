package com.etaimuallem.firebaseauth;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.security.KeyRep;

/**
 * Created by hackeru on 12/06/2017.
 */

public class AppManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //load the bootstrap icons
        TypefaceProvider.registerDefaultIconSets();
    }
}
