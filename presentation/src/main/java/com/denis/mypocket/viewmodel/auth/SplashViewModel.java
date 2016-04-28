package com.denis.mypocket.viewmodel.auth;

import android.text.TextUtils;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
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

    private boolean isUserExist = false, isTokenExist = false;

    @Inject
    public SplashViewModel(UseCase<String> tokenGetUseCase, UseCase<UserModel> userGetUseCase) {
        this.tokenGetUseCase = tokenGetUseCase;
        this.userGetUseCase = userGetUseCase;
    }

    public boolean execute() {
        tokenGetUseCase.executeSync(new TokenGetSubscriber());
        userGetUseCase.executeSync(new UserGetSubscriber());
        return isUserExist && isTokenExist;
    }

    @Override
    public void destroy() {
        tokenGetUseCase.unSubscribe();
        userGetUseCase.unSubscribe();
    }

    private class TokenGetSubscriber extends DefaultSubscriber<String> {
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
            if (!TextUtils.isEmpty(s))
                isTokenExist = true;
        }
    }

    private class UserGetSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(User user) {
            isUserExist = isUserValid(user);
        }
    }

    private boolean isUserValid(User userModel) {
        return userModel.getEmail() != null &&
                userModel.getUsername() != null &&
                userModel.getEmail() != null;
    }
}
