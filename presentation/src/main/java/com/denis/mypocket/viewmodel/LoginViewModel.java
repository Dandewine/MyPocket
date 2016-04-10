package com.denis.mypocket.viewmodel;

import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Inject;

/**
 * Created by denis on 4/2/16.
 */
@PerActivity
public class LoginViewModel implements ViewModel {


    @Inject
    public LoginViewModel() {

    }

    @Override
    public void destroy() {

    }
}
