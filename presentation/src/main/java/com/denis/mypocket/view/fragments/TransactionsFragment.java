package com.denis.mypocket.view.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentTransactionsListBinding;
import com.denis.mypocket.view.activity.AddTransactionActivity;

/**
 * Created by denis on 12/13/15.
 */
public class TransactionsFragment extends BaseFragment {

    private boolean isFabOpen = false;
    private FloatingActionButton fabMain, fabAddIncome, fabAddOutcome;
    private Animation rotate_backward, fab_close, fab_open, rotate_forward;

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
        initAnimations();
        configureViews(binding);
        return binding.getRoot();
    }

    public void configureViews(FragmentTransactionsListBinding binding) {
        fabMain = binding.fabMain;
        fabAddIncome = binding.addIncomeTrans;
        fabAddOutcome = binding.addOutcomeTrans;
        binding.fabMain.setOnClickListener(v -> animateFAB());
        fabAddIncome.setOnClickListener(v -> startAddTransactionAct());
    }

    public void animateFAB() {
        if (isFabOpen) {
            fabMain.startAnimation(rotate_backward);
            fabAddIncome.startAnimation(fab_close);
            fabAddOutcome.startAnimation(fab_close);
            fabAddIncome.setClickable(false);
            fabAddOutcome.setClickable(false);
            isFabOpen = false;

        } else {
            fabMain.startAnimation(rotate_forward);
            fabAddIncome.startAnimation(fab_open);
            fabAddOutcome.startAnimation(fab_open);
            fabAddIncome.setClickable(true);
            fabAddOutcome.setClickable(true);
            isFabOpen = true;
        }
    }

    public void startAddTransactionAct(){
        Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), fabAddIncome, "reveal");
        ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());

    }

    public void initAnimations() {
        rotate_backward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
    }

}
