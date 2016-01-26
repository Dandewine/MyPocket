package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.MaterialDrawerActivityBinding;
import com.denis.mypocket.view.adapter.DrawerAdapter;
import com.denis.mypocket.view.fragments.TransactionsFragment;

/**
 * Created by denis on 12/13/15.
 */
public class DrawerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialDrawerActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.material_drawer_activity);

        DrawerLayout mDrawerLayout = binding.drawerLayout;
        //mDrawerLayout.setStatusBarBackgroundColor(getColor(R.color.colorPrimaryDark));

        RecyclerView recyclerView = binding.recycler;
        configireToolbar(binding.toolBarInclude.toolbar,R.string.app_name,false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DrawerAdapter());
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        addFragment(R.id.content_frame, TransactionsFragment.newInstance());
    }

    @Override
    protected void initDIComponent() {

    }
}
