package com.denis.mypocket;

import android.app.Application;

import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.components.DaggerApplicationComponent;
import com.denis.mypocket.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

public class MyPocketApp extends Application {
    private ApplicationComponent component;
    private static MyPocketApp pocketApp;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initializeInjector();
        pocketApp = this;
    }

    public static MyPocketApp getPocketApp() {
        return pocketApp;
    }

    private void initializeInjector() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
