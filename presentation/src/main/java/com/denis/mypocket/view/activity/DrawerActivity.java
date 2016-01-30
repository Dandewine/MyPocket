package com.denis.mypocket.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.denis.mypocket.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityDrawerBinding;
import com.denis.mypocket.databinding.DrawersContentActivityBinding;

public class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    private Animation rotate_backward, fab_close, fab_open, rotate_forward;
    FloatingActionButton fabMain, fabAddIncome, fabAddOutcome;
    boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDrawerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer);
        Toolbar toolbar = binding.appBer.includeToolbar.toolbar;
        configireToolbar(toolbar, R.string.app_name, false);
        configureViews(binding.appBer);
        initAnimations();


        drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initAnimations() {
        rotate_backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
    }

    public void configureViews(DrawersContentActivityBinding binding) {
        fabMain = binding.fabMain;
        fabAddIncome = binding.addIncomeTrans;
        fabAddOutcome = binding.addOutcomeTrans;
        binding.fabMain.setOnClickListener(v -> animateFAB());
        fabAddIncome.setOnClickListener(v -> startAddTransactionAct(true));
        fabAddOutcome.setOnClickListener(v -> startAddTransactionAct(false));
    }

    public void animateFAB() {
        if (isFabOpen) {
            fabMain.startAnimation(rotate_backward);
            fabAddIncome.startAnimation(fab_close);
            fabAddOutcome.startAnimation(fab_close);
            fabAddIncome.setClickable(false);
            fabAddOutcome.setClickable(false);
            isFabOpen = false;

        } else {
            fabMain.startAnimation(rotate_forward);
            fabAddIncome.startAnimation(fab_open);
            fabAddOutcome.startAnimation(fab_open);
            fabAddIncome.setClickable(true);
            fabAddOutcome.setClickable(true);
            isFabOpen = true;
        }
    }

    public void startAddTransactionAct(boolean isIncome) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        intent.putExtra(PLConstants.INTENT_INCOME_FLAG, isIncome);
        startActivity(intent);

        /*ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, fabAddIncome, "reveal");
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());*/
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wallets) {

        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_debts) {

        } else if (id == R.id.travel_mode) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_tutorials) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void initDIComponent() {

    }
}
