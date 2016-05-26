package com.denis.mypocket.viewmodel.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.R;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.view.add_wallet.WalletActivity;
import com.denis.mypocket.view.activity.DrawerActivity;
import com.denis.mypocket.view.activity.SignUpActivity;
import com.denis.mypocket.viewmodel.ViewModel;
import com.google.gson.Gson;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by denis on 4/22/16.
 */
public class LoginViewModel implements ViewModel {

    private UseCase<String> loginUserCase;
    private UseCase<String> tokenSaveUseCase;
    private UseCase<User> userSaveUseCase;
    private UseCase<Wallet> getWalletUseCase;
    private UseCase<List<Wallet>> saveWalletUseCase;

    public String email = "", password = "";
    private Context context;

    public boolean isPasswordValid;
    public boolean isEmailValid;

    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableInt prorgessBarVisibility = new ObservableInt(View.GONE);

    private ClearBlankSpaceCallback blankSpaceCallback;

    public LoginViewModel(UseCase<String> loginUserCase,
                          UseCase<String> tokenSaveUseCase,
                          UseCase<User> userSaveUseCase,
                          UseCase<Wallet> getWalletUseCase,
                          UseCase<List<Wallet>> walletsSave, Context context) {
        this.loginUserCase = loginUserCase;
        this.tokenSaveUseCase = tokenSaveUseCase;
        this.userSaveUseCase = userSaveUseCase;
        this.getWalletUseCase = getWalletUseCase;
        this.saveWalletUseCase = walletsSave;
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
            prorgessBarVisibility.set(View.VISIBLE);
            UserModel model = new UserModel(email, password);
            String body = new Gson().toJson(model);
            loginUserCase.executeAsync(new LoginSubscriber(), body);
        } else {
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
        userSaveUseCase.unSubscribe();
        tokenSaveUseCase.unSubscribe();
    }

    private class LoginSubscriber extends DefaultSubscriber<LoginResponse> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(LoginResponse data) {
            if (data != null) {
                //startDrawerActivity();
                saveUserData(data.getToken(), data.getUser());
            } else {
                prorgessBarVisibility.set(View.GONE);
            }
        }
    }

    private void startDrawerActivity() {
        Intent intent = DrawerActivity.getCallingIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    private void startCreateWalletActivity() {
        Intent intent = WalletActivity.getCallingIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    private void saveUserData(String token, User user) {
        userSaveUseCase.executeSync(new DefaultSubscriber<String>(), user);
        tokenSaveUseCase.executeSync(new TokenSubscriber(), token);
    }

    private class TokenSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onNext(String s) {
            getWalletUseCase.executeAsync(new WalletsSubscriber());
        }
    }

    private class WalletsSubscriber extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onNext(List<Wallet> wallets) {
            if (wallets == null || wallets.isEmpty())
                startCreateWalletActivity();
            else {
                saveWalletUseCase.executeSync(new SaveWalletSubscriber(), wallets);
            }

        }
    }

    private class SaveWalletSubscriber extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onNext(List<Wallet> wallets) {
            startDrawerActivity();
        }
    }


    public interface ClearBlankSpaceCallback {
        void clear();
    }

    public void setBlankSpaceCallback(ClearBlankSpaceCallback blankSpaceCallback) {
        this.blankSpaceCallback = blankSpaceCallback;
    }
}
