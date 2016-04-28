package com.denis.mypocket.viewmodel.auth;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.viewmodel.ViewModel;

import javax.inject.Inject;

/**
 * Created by denis on 4/25/16.
 */
@PerActivity
public class SplashViewModel implements ViewModel {
    private UseCase<String> tokenGetUseCase;
    private UseCase<UserModel> userGetUseCase;

    @Inject
    public SplashViewModel(UseCase<String> tokenGetUseCase, UseCase<UserModel> userGetUseCase) {
        this.tokenGetUseCase = tokenGetUseCase;
        this.userGetUseCase = userGetUseCase;
    }

    public void execute(){
        tokenGetUseCase.executeSync(new TokenGetSubscriber());
        userGetUseCase.executeSync(new UserGetSubscriber());
    }

    @Override
    public void destroy() {

    }

    private class TokenGetSubscriber extends DefaultSubscriber<String>{
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(String s) {
            super.onNext(s);
        }
    }

    private class UserGetSubscriber extends DefaultSubscriber<UserModel>{
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(UserModel userModel) {
            super.onNext(userModel);
        }
    }
}
