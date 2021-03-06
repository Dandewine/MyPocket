package com.denis.mypocket.screens.login_screen.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.denis.mypocket.utils.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityLoginBinding;
import com.denis.mypocket.internal.di.components.DaggerLoginComponent;
import com.denis.mypocket.internal.di.modules.LoginModule;
import com.denis.mypocket.view.activity.BaseActivity;
import com.denis.mypocket.screens.login_screen.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class SigInActivity extends BaseActivity implements LoginViewModel.ClearBlankSpaceCallback {

    @Inject LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    public static Intent getCallingIntent(Context context, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, SigInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PLConstants.EMAIL_INTENT, bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel.setBlankSpaceCallback(this);

        Bundle bundle = getIntent().getBundleExtra(PLConstants.EMAIL_INTENT);
        if (bundle != null)
            viewModel.email = bundle.getString(PLConstants.EMAIL_INTENT);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .loginModule(new LoginModule())
                .build().inject(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void clear() {
        binding.inputEmailAL.setErrorEnabled(false);
        binding.inputPasswordAL.setErrorEnabled(!viewModel.isPasswordValid);
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }// TODO: 5/19/16 Create message for user if he trying to log in when DB hasn't record
}
