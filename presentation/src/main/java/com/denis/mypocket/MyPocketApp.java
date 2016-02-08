package com.denis.mypocket;

import android.app.Application;

import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.components.DaggerApplicationComponent;
import com.denis.mypocket.internal.di.modules.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyPocketApp extends Application {
    private ApplicationComponent component;
    private static MyPocketApp pocketApp;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        configureRealm();
        pocketApp = this;


    }

    public static MyPocketApp getPocketApp() {
        return pocketApp;
    }

    private void initializeInjector(){
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    private void configureRealm(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
