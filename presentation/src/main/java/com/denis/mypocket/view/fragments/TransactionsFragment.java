package com.denis.mypocket.view.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentTransactionsListBinding;
import com.denis.mypocket.view.activity.AddTransactionActivity;

/**
 * Created by denis on 12/13/15.
 */
public class TransactionsFragment extends BaseFragment{

    public static TransactionsFragment newInstance() {
        Bundle args = new Bundle();
        TransactionsFragment fragment = new TransactionsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTransactionsListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transactions_list, container, false);

        binding.addTransFab.setOnClickListener(
                v -> getActivity().startActivity(new Intent(getActivity(),AddTransactionActivity.class)));

        return binding.getRoot();
    }
}
