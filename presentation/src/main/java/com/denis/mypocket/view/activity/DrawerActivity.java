package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.denis.domain.models.Transaction;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityDrawerBinding;
import com.denis.mypocket.view.fragments.CycleOperationFragment;
import com.denis.mypocket.view.fragments.DebtsFragment;
import com.denis.mypocket.view.fragments.TransactionsFragment;

public class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDrawerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer);

        Toolbar toolbar = binding.content.toolbar;
        configireToolbar(toolbar, R.string.app_name, false);

        bindDrawer(binding,toolbar);

        bindTabs(binding);
    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
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

    private void bindTabs(ActivityDrawerBinding binding){
        mTabLayout = binding.content.tabs;
        mViewPager = binding.content.viewPagerContainer;

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void bindDrawer(ActivityDrawerBinding binding, Toolbar toolbar){
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        mDrawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wallets) {
            //startActivity(new Intent(this,WalletsActivity.class));
        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_debts) {
            replaceFragment(R.id.containerDrawer, DebtsFragment.newInstance());
        } else if (id == R.id.travel_mode) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_tutorials) {

        } else if (id == R.id.nav_cycle_operations) {
            replaceFragment(R.id.containerDrawer, CycleOperationFragment.newInstance());
        } else if (id == R.id.nav_transactions) {
            replaceFragment(R.id.containerDrawer, TransactionsFragment.newInstance());
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void initDIComponent() {

    }

    /**
     * A {@link FragmentStatePagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public  class SectionsPagerAdapter extends FragmentStatePagerAdapter {

       @DrawableRes int[] tabsIndicators = {R.drawable.ic_menu_camera,
       R.drawable.vector_cycle, R.drawable.vector_saves, R.drawable.vector_wallet};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return TransactionsFragment.newInstance();
                case 1: return CycleOperationFragment.newInstance();
                case 2: return CycleOperationFragment.newInstance();
                case 3: return CycleOperationFragment.newInstance();
                default: throw new IllegalStateException("Can't recognize which fragment I should give to");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            /*Drawable vectorDrawable = ContextCompat.getDrawable(DrawerActivity.this,tabsIndicators[position]);
            vectorDrawable.setBounds(0,0, 24, 24);
            SpannableString sb = new SpannableString(" "+position);
            ImageSpan imageSpan = new ImageSpan(vectorDrawable, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;*/

            switch (position) {
                case 0:
                    return "Trans";
                case 1:
                    return "Cyclic";
                case 2:
                    return "Saves";
                case 3: return "Stats";
                default: throw new IllegalStateException("Can't find title.");
            }
        }


    }
}
