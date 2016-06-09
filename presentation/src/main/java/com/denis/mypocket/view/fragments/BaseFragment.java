package com.denis.mypocket.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.FragmentModule;
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

    protected ApplicationComponent getApplicationComponent(){
        return getBaseActivity().getApplicationComponent();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    protected abstract void initDI();
}
