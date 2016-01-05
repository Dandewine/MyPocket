package com.denis.mypocket.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityWalletsBinding;

/**
 * Created by denis on 1/4/16.
 */
public class WalletsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWalletsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_wallets);
        configireToolbar(binding.walletsToolbar.toolbar,R.string.drawer_wallets,true);

        binding.fab.setOnClickListener(v ->
                startActivity(new Intent(this,AddWalletActivity.class)));
    }
}
