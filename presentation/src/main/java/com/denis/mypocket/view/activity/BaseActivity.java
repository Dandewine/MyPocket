package com.denis.mypocket.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.denis.mypocket.AppNavigator;
import com.denis.mypocket.MyPocketApp;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.ActivityModule;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent applicationComponent;
    //AppNavigator navigator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initDIComponent();
        super.onCreate(savedInstanceState);
        applicationComponent = getApplicationComponent();
        applicationComponent.inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.denis.mypocket.internal.di.components.ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        return ((MyPocketApp) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.denis.mypocket.internal.di.modules.ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void configireToolbar(Toolbar toolbar,
                                    @StringRes int text,
                                    boolean asHomeEnabled) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(asHomeEnabled);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    public void addFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }




   /* protected <T extends View>T myFindViewById(@IdRes int id){
        return (T)(findViewById(id));
    }*/

    protected abstract void initDIComponent();


}
