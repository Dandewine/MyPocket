package com.denis.mypocket.view.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentCycleOperationBinding;
import com.denis.mypocket.view.activity.AddCycleOperationActivity;

public class CycleOperationFragment extends BaseFragment {

    public static CycleOperationFragment newInstance() {
        Bundle args = new Bundle();
        CycleOperationFragment fragment = new CycleOperationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCycleOperationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cycle_operation, container, false);
        binding.fabAddCO.setOnClickListener(v -> startActivity(new Intent(getActivity(), AddCycleOperationActivity.class)));
        return binding.getRoot();
    }
}
