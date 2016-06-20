package com.denis.mypocket.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;

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
        return inflater.inflate(R.layout.fragment_cycle_operation,container,false);
    }

    @Override
    protected void initDI() {
    }
}
