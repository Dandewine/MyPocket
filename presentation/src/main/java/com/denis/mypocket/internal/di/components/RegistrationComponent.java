package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.RegistrationModule;
import com.denis.mypocket.screens.signup_screen.view.SignUpActivity;

import dagger.Component;

/**
 * Created by denis on 4/15/16.
 */
@PerActivity
@Component(modules = RegistrationModule.class, dependencies = ApplicationComponent.class)
public interface RegistrationComponent {
    void inject(SignUpActivity signUpActivity);
}
