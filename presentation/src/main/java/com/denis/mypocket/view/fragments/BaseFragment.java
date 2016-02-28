package com.denis.mypocket.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.denis.mypocket.view.activity.BaseActivity;

/**
 * Created by denis on 12/27/15.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
    }

    protected BaseActivity getBaseActivity(){
        return (BaseActivity)getActivity();
    }

    protected abstract void initDI();
}
