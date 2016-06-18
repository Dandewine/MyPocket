package com.denis.mypocket.screens.splash_screen.viewmodel;

import android.text.TextUtils;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.screens.ViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by denis on 4/25/16.
 */
@PerActivity
public class SplashViewModel implements ViewModel {
    private UseCase<String> tokenGetUseCase;
    private UseCase<User> userGetUseCase;

    private boolean isUserExist = false, isTokenExist = false;

    @Inject
    public SplashViewModel(UseCase<String> tokenGetUseCase, UseCase<User> userGetUseCase) {
        this.tokenGetUseCase = tokenGetUseCase;
        this.userGetUseCase = userGetUseCase;
    }

    public Observable<Boolean> execute() {
        tokenGetUseCase.executeSync(new TokenGetSubscriber());
        userGetUseCase.executeSync(new UserGetSubscriber());
        return Observable.just(isUserExist && isTokenExist);
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

    private class UserGetSubscriber extends DefaultSubscriber<List<User>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<User> users) {
            if (users != null && !users.isEmpty())
                isUserExist = isUserValid(users.get(0));

        }

        private boolean isUserValid(User userModel) {
            return userModel.getEmail() != null &&
                    userModel.getUsername() != null &&
                    userModel.getEmail() != null;
        }
    }
}
