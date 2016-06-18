package com.denis.mypocket.screens.signup_screen.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivitySignupBinding;
import com.denis.mypocket.internal.di.components.DaggerRegistrationComponent;
import com.denis.mypocket.internal.di.modules.RegistrationModule;
import com.denis.mypocket.screens.signup_screen.viewmodel.RegistrationViewModel;
import com.denis.mypocket.screens.BaseActivity;

import javax.inject.Inject;

/**
 * Created by denis on 4/2/16.
 */
public class SignUpActivity extends BaseActivity implements RegistrationViewModel.ClearBlankSpaceCallback {

    @Inject
    public RegistrationViewModel viewModel;
    private ActivitySignupBinding binding;

    public static Intent getCallingIntent(Context context){
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        viewModel.setBlankSpaceCallback(this);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        DaggerRegistrationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .registrationModule(new RegistrationModule())
                .activityModule(getActivityModule())
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }

    @Override
    public void clear() {
        binding.textInputLayoutPasswordAS.setErrorEnabled(!viewModel.isPasswordValid);
        binding.textInputLatourNameAS.setErrorEnabled(!viewModel.isUsernameValid);
        binding.textInputLayoutEmailAS.setErrorEnabled(!viewModel.isEmailValid);
    }
}
