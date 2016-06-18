package com.denis.mypocket.screens.login_screen.viewmodel;

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
import com.denis.domain.interactor.auth.LoginInteractorFacade;
import com.denis.domain.models.categories.ExpenseCategory;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.screens.signup_screen.view.SignUpActivity;
import com.denis.mypocket.screens.tab_with_drawer_screen.view.DrawerActivity;
import com.denis.mypocket.screens.wallets_screen.view.WalletActivity;
import com.denis.mypocket.screens.ViewModel;
import com.google.gson.Gson;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * @author Denis_Zinkovskiy
 */

@PerActivity
public class LoginViewModel implements ViewModel {

    private LoginInteractorFacade interactorFacade;

    public String email = "", password = "";
    private Context context;

    public boolean isPasswordValid;
    public boolean isEmailValid;

    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableInt progressBarVisibility = new ObservableInt(View.GONE);

    private ClearBlankSpaceCallback blankSpaceCallback;

    @Inject
    public LoginViewModel(Context context, LoginInteractorFacade facade) {
        this.interactorFacade = facade;
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
            progressBarVisibility.set(View.VISIBLE);
            UserModel model = new UserModel(email, password);
            String body = new Gson().toJson(model);
            interactorFacade.login(new LoginSubscriber(), body);
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
        context = null;
        interactorFacade.destroy();
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
                saveUserData(data.getToken(), data.getUser(), data.getIncomeCategories(), data.getExpenseCategories());
            } else {
                progressBarVisibility.set(View.GONE);
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
        Intent intent = WalletActivity.getCallingIntent(context, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    private void saveUserData(String token, User user, List<IncomeCategory> incomeCategories, List<ExpenseCategory> expenseCategories) {
        interactorFacade.saveUser(new DefaultSubscriber<String>(), user);
        interactorFacade.saveToken(new TokenSubscriber(), token);
        interactorFacade.saveExpenseCategories(new DefaultSubscriber<>(), expenseCategories);
        interactorFacade.saveIncomeCategories(new DefaultSubscriber<>(), incomeCategories);
    }

    private class TokenSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onNext(String s) {
            interactorFacade.getWallets(new WalletsSubscriber());
        }
    }

    private class WalletsSubscriber extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onNext(List<Wallet> wallets) {
            if (wallets == null || wallets.isEmpty())
                startCreateWalletActivity();
            else {
                interactorFacade.saveWallets(new SaveWalletSubscriber(), wallets);
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
