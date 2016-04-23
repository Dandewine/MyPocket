package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.LoginModule;
import com.denis.mypocket.view.activity.SigInActivity;

import dagger.Component;

/**
 * Created by denis on 4/23/16.
 */
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
@PerActivity
public interface LoginComponent {
    void inject(SigInActivity activity);
}
