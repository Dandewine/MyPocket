package com.denis.mypocket.view.activity;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by denis on 12/13/15.
 */
public class BaseActivity extends AppCompatActivity {

    protected void configireToolbar(Toolbar toolbar,
                                    @StringRes int text,
                                    boolean asHomeEnabled){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(asHomeEnabled);
    }

    protected <T extends View>T myFindViewById(@IdRes int id){
        return (T)(findViewById(id));
    }
}
