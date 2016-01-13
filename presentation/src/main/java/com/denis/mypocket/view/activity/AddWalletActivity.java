package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.domain.interactor.AddWalletUseCase;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddWalletBinding;
import com.denis.mypocket.internal.di.components.DaggerWalletsComponent;
import com.denis.mypocket.internal.di.components.WalletsComponent;
import com.denis.mypocket.viewmodel.AddWalletViewModel;

import javax.inject.Inject;

public class AddWalletActivity extends BaseActivity{

    private AddWalletViewModel viewModel;
    @Inject public AddWalletUseCase addWalletUseCase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WalletsComponent component = DaggerWalletsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        component.inject(this);


        ActivityAddWalletBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_wallet);
        configireToolbar(binding.addWalletToolbar.toolbar,R.string.toolbar_add_wallet,true);

        viewModel = new AddWalletViewModel(addWalletUseCase);
        binding.setViewModel(viewModel);

    }
}
