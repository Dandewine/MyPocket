package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddWalletBinding;
import com.denis.mypocket.internal.di.components.DaggerWalletsComponent;
import com.denis.mypocket.internal.di.modules.WalletModule;
import com.denis.mypocket.viewmodel.adding.AddWalletViewModel;

import javax.inject.Inject;

public class AddWalletActivity extends BaseActivity{

    @Inject public AddWalletViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddWalletBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_wallet);
        configireToolbar(binding.addWalletToolbar.toolbar,R.string.toolbar_add_wallet,true);

        binding.setViewModel(viewModel);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initDIComponent() {
        DaggerWalletsComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .walletModule(new WalletModule())
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }
}
