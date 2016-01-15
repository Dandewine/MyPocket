package com.denis.mypocket.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.view.activity.BaseActivity;

/**
 * Created by denis on 12/27/15.
 */
public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected BaseActivity getBaseActivity(){
        return (BaseActivity)getActivity();
    }
}
