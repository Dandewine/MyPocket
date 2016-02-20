package com.denis.mypocket.view.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.viewmodel.adding.AddDebtViewModel;

import javax.inject.Inject;

/**
 * Created by denis on 2/20/16.
 */
public class DebtsFragment extends BaseFragment {

    public static DebtsFragment newInstance() {
        Bundle args = new Bundle();
        DebtsFragment fragment = new DebtsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_debts, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initDI() {

    }
}
