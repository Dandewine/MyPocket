package com.denis.mypocket.view.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentTransactionsListBinding;

/**
 * Created by denis on 12/13/15.
 */
public class TransactionsFragment extends BaseFragment {

    private Animation rotate_backward, fab_close, fab_open, rotate_forward;
   // FloatingActionButton fabMain, fabAddIncome, fabAddOutcome;
    boolean isFabOpen = false;

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
        //initAnimations();
        //configureViews(binding);
        return binding.getRoot();
    }

/*
    public void initAnimations() {
        rotate_backward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
    }

    public void configureViews(FragmentTransactionsListBinding binding) {
        fabMain = binding.fabMain;
        fabAddIncome = binding.addIncomeTrans;
        fabAddOutcome = binding.addExpense;
        binding.fabMain.setOnClickListener(v -> animateFAB());
        fabAddIncome.setOnClickListener(v -> startAddTransactionAct(true));
        fabAddOutcome.setOnClickListener(v -> startAddTransactionAct(false));
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

    public void startAddTransactionAct(boolean isIncome) {
        Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
        intent.putExtra(PLConstants.INTENT_INCOME_FLAG, isIncome);
        startActivity(intent);

        *//*ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, fabAddIncome, "reveal");
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());*//*
    }*/

    @Override
    protected void initDI() {

    }
}
