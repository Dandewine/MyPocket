package com.denis.mypocket.screens.transactions_tab_screen.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentTransactionsListBinding;
import com.denis.mypocket.internal.di.components.DaggerTransactionTabComponent;
import com.denis.mypocket.internal.di.modules.transactions.TransactionTabModule;
import com.denis.mypocket.model.TransactionModel;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.screens.transactions_tab_screen.viewmodel.TransactionViewModel;
import com.denis.mypocket.view.fragments.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by denis on 12/13/15.
 */
public class TransactionsFragment extends BaseFragment {

    private static final String TRANSACTIONS_BUNDLE_KEY = "transaction.bundle";

    public static TransactionsFragment newInstance(ArrayList<WalletModel> list) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(TRANSACTIONS_BUNDLE_KEY,list);
        TransactionsFragment fragment = new TransactionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject TransactionViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTransactionsListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transactions_list, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    protected void initDI() {
        ArrayList<WalletModel> wallets = getArguments().getParcelableArrayList(TRANSACTIONS_BUNDLE_KEY);
        TransactionTabModule module = new TransactionTabModule(wallets);

        DaggerTransactionTabComponent.builder()
                .applicationComponent(getApplicationComponent())
                .fragmentModule(getFragmentModule())
                .transactionTabModule(module)
                .build().inject(this);
    }

    public void updateDataSet(TransactionModel transaction) {
        viewModel.updateDataSet(transaction);
    }

}
