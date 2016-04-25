package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.R;

/**
 * Created by denis on 4/25/16.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    @Override
    protected void initDIComponent() {

    }
}
