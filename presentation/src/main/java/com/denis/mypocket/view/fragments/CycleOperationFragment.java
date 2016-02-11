package com.denis.mypocket.view.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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

    private FragmentCycleOperationBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cycle_operation, container, false);
        binding.fabAddCO.setOnClickListener(v -> startTransition());
        return binding.getRoot();
    }

    private void startTransition(){
        Intent intent = new Intent(getActivity(), AddCycleOperationActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), binding.fabAddCO, binding.fabAddCO.getTransitionName());
        ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
    }
}
