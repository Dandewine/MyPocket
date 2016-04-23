package com.denis.mypocket.viewmodel.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.mypocket.PLConstants;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.view.activity.SigInActivity;
import com.denis.mypocket.view.activity.SignUpActivity;
import com.denis.mypocket.viewmodel.ViewModel;
import com.google.gson.Gson;

/**
 * Created by denis on 4/22/16.
 */
public class LoginViewModel implements ViewModel {
    private UseCase<String> loginUserCase;
    public String email, password;
    private Context context;

    public LoginViewModel(UseCase<String> loginUserCase, Context context) {
        this.loginUserCase = loginUserCase;
        this.context = context;
    }

    public View.OnClickListener login = v -> execute();

    public View.OnClickListener close = v -> {
        Intent intent = SignUpActivity.getCallingIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    };

    private void execute() {
        UserModel model = new UserModel(email, password);
        String body = new Gson().toJson(model);
        loginUserCase.executeAsync(new LoginSubscriber(), body);
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
        loginUserCase.unSubscribe();
    }

    private class LoginSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(String token) {
            if (token != null) {
                SharedPreferences preferences = context.getSharedPreferences(PLConstants.TOKEN_SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(PLConstants.TOKEN_SHARED_PREFS, token);
                editor.apply();
            }
        }
    }
}
