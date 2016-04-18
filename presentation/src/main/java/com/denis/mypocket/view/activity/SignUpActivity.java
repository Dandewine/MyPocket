package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityLoginBinding;
import com.denis.mypocket.databinding.ActivitySignupBinding;
import com.denis.mypocket.internal.di.components.DaggerRegistrationComponent;
import com.denis.mypocket.internal.di.modules.RegistrationModule;
import com.denis.mypocket.viewmodel.RegistrationViewModel;

import javax.inject.Inject;

/**
 * Created by denis on 4/2/16.
 */
public class SignUpActivity extends BaseActivity {


    @Inject public RegistrationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        Log.d("myTag", "onCreate: viewModel is null = "+(viewModel == null));
        binding.setViewModel(viewModel);

    }

    @Override
    protected void initDIComponent() {
        DaggerRegistrationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .registrationModule(new RegistrationModule())
                .build().inject(this);
    }
}
