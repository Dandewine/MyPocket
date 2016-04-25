package com.denis.mypocket.viewmodel.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.MainThread;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.mypocket.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.view.activity.DrawerActivity;
import com.denis.mypocket.view.activity.SignUpActivity;
import com.denis.mypocket.viewmodel.ViewModel;
import com.google.gson.Gson;

import java.util.regex.Pattern;

import rx.Observer;
import rx.observers.SafeSubscriber;

/**
 * Created by denis on 4/22/16.
 */
public class LoginViewModel implements ViewModel {
    private UseCase<String> loginUserCase, tokenSaveUseCase;
    public String email = "", password = "";
    private Context context;

    public boolean isPasswordValid;
    public boolean isEmailValid;

    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableInt prorgessBarVisibility = new ObservableInt(View.GONE);

    private ClearBlankSpaceCallback blankSpaceCallback;

    public LoginViewModel(UseCase<String> loginUserCase, UseCase<String> tokenSaveUseCase, Context context) {
        this.loginUserCase = loginUserCase;
        this.tokenSaveUseCase = tokenSaveUseCase;
        this.context = context;
    }

    public View.OnClickListener login = v -> execute();

    public View.OnClickListener close = v -> {
        Intent intent = SignUpActivity.getCallingIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    };

    private void execute() {
        if (isEmailValid && isPasswordValid) {
            UserModel model = new UserModel(email, password);
            String body = new Gson().toJson(model);
            loginUserCase.executeAsync(new LoginSubscriber(), body);
        }else{
            validatePassword();
            validateEmail();
        }
    }

    private void validateEmail() {
        String mandatory = context.getResources().getString(R.string.field_empty);

        if (TextUtils.isEmpty(email)) {
            emailError.set(mandatory);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError.set(context.getResources().getString(R.string.email_error));
            Log.d("myTag", "email is not valid");
        } else {
            emailError.set(null);
            blankSpaceCallback.clear();
        }

        isEmailValid = emailError.get() == null;
    }

    private void validatePassword() {
        String mandatory = context.getResources().getString(R.string.field_empty);

        if (TextUtils.isEmpty(password)) {
            passwordError.set(mandatory);
        }

        if (!Pattern.matches("^[a-zA-Z0-9._-]{6,20}$", password)) {
            passwordError.set(context.getResources().getString(R.string.password_error));
            Log.d("myTag", "password is not valid");
        } else {
            passwordError.set(null);
            blankSpaceCallback.clear();
        }

        isPasswordValid = passwordError.get() == null;
    }

    public void setEmail(Editable s) {
        if (!TextUtils.equals(s.toString(), email))
            email = s.toString();
        validateEmail();
    }

    public void setPassword(Editable s) {
        if (!TextUtils.equals(s.toString(), password))
            password = s.toString();
        validatePassword();
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
                tokenSaveUseCase.executeSync(new TokenSubscriber(), token);

                Intent intent = DrawerActivity.getCallingIntent(context);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        }
    }

    private static class TokenSubscriber extends DefaultSubscriber<String> {
    }

    public interface ClearBlankSpaceCallback {
        void clear();
    }

    public void setBlankSpaceCallback(ClearBlankSpaceCallback blankSpaceCallback) {
        this.blankSpaceCallback = blankSpaceCallback;
    }
}
