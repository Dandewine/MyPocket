package com.denis.mypocket.viewmodel;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.mypocket.model.UserModel;
import com.google.gson.Gson;

/**
 * Created by denis on 4/22/16.
 */
public class LoginViewModel implements ViewModel {
    private UseCase<String> loginUserCase;
    public String email, password;

    public LoginViewModel(UseCase<String> loginUserCase) {
        this.loginUserCase = loginUserCase;
    }

    public View.OnClickListener login = v -> execute();

    private void execute() {
        UserModel model = new UserModel(email, password);
        String body = new Gson().toJson(model);
        loginUserCase.executeAsync(new LoginSubscriber(),body);
    }

    public void setEmail(Editable s) {
        if (!TextUtils.equals(s.toString(), email))
            email = s.toString();
    }

    public void setPassword(Editable s) {
        if (!TextUtils.equals(s.toString(), password))
            password = s.toString();
    }

    @Override
    public void destroy() {

    }

    private class LoginSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(User user) {
            Log.d("myTag", "user is = " + user);
        }
    }
}
