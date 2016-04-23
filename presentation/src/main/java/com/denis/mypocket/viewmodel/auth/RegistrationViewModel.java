package com.denis.mypocket.viewmodel.auth;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableByte;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;

import com.denis.mypocket.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.view.activity.SigInActivity;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Created by denis on 4/2/16.
 */
@PerActivity
public class RegistrationViewModel implements ViewModel {

    private UseCase<User> registrationUseCase;

    public ObservableField<String> userNameError = new ObservableField<>();
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();


    public boolean isUsernameValid;
    public boolean isPasswordValid;
    public boolean isEmailValid;

    public String userName = "";
    public String email = "";
    public String password = "";

    public ObservableInt prorgessBarVisibility = new ObservableInt(View.GONE);

    private Context context;
    //cause for this callback that android sdk has a problem with InputTextLayout error handling
    //they make errors invisible after successful validation, so we will have blank spaces under the edittext
    //solution is to crete your own TIL with setErrorEnabled method and call it with false parameter after successful validation
    private ClearBlankSpaceCallback blankSpaceCallback;

    @Inject
    public RegistrationViewModel(UseCase<User> registrationUseCase, Context context) {
        this.registrationUseCase = registrationUseCase;
        this.context = context;
    }

    public View.OnClickListener onClick = v -> execute();

    public View.OnClickListener gotoLogin = v -> ((Activity) context).finish();

    private void execute() {
        if (isPasswordValid && isUsernameValid && isEmailValid) {
            prorgessBarVisibility.set(View.VISIBLE);
            User user = getUserTest();
            registrationUseCase.executeAsync(new RegistrationSubscriber(), user);
        }
    }

    private void validateUserName() {
        String mandatory = context.getResources().getString(R.string.field_empty);
        if (TextUtils.isEmpty(userName)) {
            userNameError.set(mandatory);
        }
        if (!Pattern.matches("^[a-zA-Z0-9._-]{3,10}$", userName)) {
            userNameError.set(context.getResources().getString(R.string.username_not_valid));
            Log.d("myTag", "username is not valid");
        } else {
            userNameError.set(null);
            blankSpaceCallback.clear();
        }
        isUsernameValid = userNameError.get() == null;
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

    private User getUserTest() {
        User user = new User();
        user.setEmail(email);
        user.setName(userName);
        user.setPassword(password);
        return user;
    }

    public void setUsername(Editable s) {
        if (!TextUtils.equals(s.toString(), userName))
            userName = s.toString();
        validateUserName();
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
        registrationUseCase.unSubscribe();
    }


    private class RegistrationSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onCompleted() {
            prorgessBarVisibility.set(View.GONE);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            prorgessBarVisibility.set(View.GONE);
        }

        @Override
        public void onNext(User user) {
            prorgessBarVisibility.set(View.GONE);
            if (user != null) {
                Bundle bundle = new Bundle();
                bundle.putString(PLConstants.EMAIL_INTENT, email);
                context.startActivity(SigInActivity.getCallingIntent(context, bundle));
            } else
                Toast.makeText(context, "Probably user already exists, or problem with internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public interface ClearBlankSpaceCallback {
        void clear();
    }

    public void setBlankSpaceCallback(ClearBlankSpaceCallback blankSpaceCallback) {
        this.blankSpaceCallback = blankSpaceCallback;
    }
}
