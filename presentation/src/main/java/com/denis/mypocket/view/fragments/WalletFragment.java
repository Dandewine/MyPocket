package com.denis.mypocket.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentWalletsBinding;
import com.denis.mypocket.internal.di.components.fragment.DaggerWalletFragComponent;
import com.denis.mypocket.internal.di.modules.WalletGetModule;
import com.denis.mypocket.viewmodel.getting.GetAllWalletViewModel;

import javax.inject.Inject;

public class WalletFragment extends BaseFragment{

    public static WalletFragment newInstance() {
        Bundle args = new Bundle();
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    public GetAllWalletViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentWalletsBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallets, container, false);
        dataBinding.setViewModel(viewModel);
        return dataBinding.getRoot();
    }

    @Override
    protected void initDI() {
        DaggerWalletFragComponent.builder()
                .applicationComponent(getBaseActivity().getApplicationComponent())
                .walletGetModule(new WalletGetModule())
                .build().inject(this);
    }
}
