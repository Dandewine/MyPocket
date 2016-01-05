package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddWalletBinding;

public class AddWalletActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddWalletBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_wallet);
        configireToolbar(binding.addWalletToolbar.toolbar,R.string.toolbar_add_wallet,true);
    }
}
