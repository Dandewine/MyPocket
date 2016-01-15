package com.denis.mypocket.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.denis.mypocket.MyPocketApp;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent applicationComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationComponent = getApplicationComponent();
        applicationComponent.inject(this);
    }

    protected void configireToolbar(Toolbar toolbar,
                                    @StringRes int text,
                                    boolean asHomeEnabled){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(asHomeEnabled);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    public void addFragment(int containerViewId, Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.denis.mypocket.internal.di.components.ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        return ((MyPocketApp)getApplication()).getApplicationComponent();
    }
    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.denis.mypocket.internal.di.modules.ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

   /* protected <T extends View>T myFindViewById(@IdRes int id){
        return (T)(findViewById(id));
    }*/


}
