package com.denis.mypocket.screens.splash_screen.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivitySplashBinding;
import com.denis.mypocket.internal.di.components.DaggerSplashComponent;
import com.denis.mypocket.internal.di.modules.SplashModule;
import com.denis.mypocket.screens.splash_screen.viewmodel.SplashViewModel;
import com.denis.mypocket.view.activity.BaseActivity;
import com.denis.mypocket.screens.tab_with_drawer_screen.view.DrawerActivity;
import com.denis.mypocket.screens.login_screen.view.SigInActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

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


        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(l -> viewModel.execute())
                .subscribe(new DefaultSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean isUserExist) {
                        if (isUserExist)
                            startTabs();
                        else
                            startLogin();
                    }
                });
    }

    private void startTabs() {
        Intent intent = DrawerActivity.getCallingIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void startLogin() {
        Intent intent = SigInActivity.getCallingIntent(this, null);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
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
