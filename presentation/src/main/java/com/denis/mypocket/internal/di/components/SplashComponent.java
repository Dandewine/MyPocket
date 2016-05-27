package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.SplashModule;
import com.denis.mypocket.screens.splash_screen.view.SplashActivity;

import dagger.Component;

/**
 * Created by denis on 4/27/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
