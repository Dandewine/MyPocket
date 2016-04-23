package com.denis.mypocket.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityLoginBinding;
import com.denis.mypocket.internal.di.components.DaggerLoginComponent;
import com.denis.mypocket.internal.di.modules.LoginModule;
import com.denis.mypocket.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class SigInActivity extends BaseActivity {

    @Inject LoginViewModel viewModel;

    public static Intent getCallingIntent(Context context, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, SigInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null)
            intent.putExtra(SigInActivity.class.getName(), bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .loginModule(new LoginModule())
                .build().inject(this);
    }
}