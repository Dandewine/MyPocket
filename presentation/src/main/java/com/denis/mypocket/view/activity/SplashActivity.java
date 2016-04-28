package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivitySplashBinding;
import com.denis.mypocket.internal.di.components.DaggerSplashComponent;
import com.denis.mypocket.internal.di.modules.SplashModule;
import com.denis.mypocket.viewmodel.auth.SplashViewModel;

import javax.inject.Inject;

/**
 * Created by denis on 4/25/16.
 */
public class SplashActivity extends BaseActivity {

    @Inject SplashViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setViewModel(viewModel);

        viewModel.execute();
    }

    @Override
    protected void initDIComponent() {
        DaggerSplashComponent.builder()
                .applicationComponent(getApplicationComponent())
                .splashModule(new SplashModule())
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }
}
