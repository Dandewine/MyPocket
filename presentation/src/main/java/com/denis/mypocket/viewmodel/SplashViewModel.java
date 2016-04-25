package com.denis.mypocket.viewmodel;

import com.denis.domain.interactor.UseCase;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Inject;

/**
 * Created by denis on 4/25/16.
 */
@PerActivity
public class SplashViewModel implements ViewModel {
    private UseCase<String> tokenGetUseCase;

    @Inject
    public SplashViewModel(UseCase<String> tokenGetUseCase) {
        this.tokenGetUseCase = tokenGetUseCase;
    }

    public SplashViewModel() {
    }

    @Override
    public void destroy() {

    }
}
