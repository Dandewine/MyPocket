package com.denis.mypocket.view.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.denis.mypocket.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityDrawerBinding;
import com.denis.mypocket.view.fragments.CycleOperationFragment;
import com.denis.mypocket.view.fragments.DebtsFragment;
import com.denis.mypocket.view.fragments.TransactionsFragment;

public class DrawerActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout mDrawer;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton fabTrans, fabCyclic, fabSaves, fabStats, fabAddIncome, fabAddExpense;

    private Animation rotate_backward, fab_close, fab_open, rotate_forward;
    private boolean isTransFabOpen;

    private boolean[] currentlyPageShown = {true, false, false, false};
    private FloatingActionButton[] fabMassive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDrawerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer);

        Toolbar toolbar = binding.content.toolbar;
        configireToolbar(toolbar, R.string.app_name, false);

        initAnimations();

        bindFabs(binding);
        bindDrawer(binding, toolbar);
        bindTabs(binding);
    }

    private void playCloseAnimation(View view) {
        AnimatorSet fabCloseSet = new AnimatorSet();

        Animator scaleAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.0f)
                .setDuration(300);
        scaleAnimatorX.setInterpolator(new AccelerateDecelerateInterpolator());

        Animator scaleAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.0f)
                .setDuration(300);
        scaleAnimatorY.setInterpolator(new AccelerateDecelerateInterpolator());

        Animator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.0f)
                .setDuration(300);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());

        fabCloseSet.playTogether(scaleAnimatorX, scaleAnimatorY, alpha);
        fabCloseSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        fabCloseSet.start();


    }

    private void playOpenAnimation(View view) {

        AnimatorSet fabOpenSet = new AnimatorSet();

        Animator scaleAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
                .setDuration(300);
        scaleAnimatorX.setInterpolator(new AccelerateDecelerateInterpolator());

        Animator scaleAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f)
                .setDuration(300);
        scaleAnimatorY.setInterpolator(new AccelerateDecelerateInterpolator());

        Animator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                .setDuration(300);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());

        fabOpenSet.setStartDelay(400);
        fabOpenSet.playTogether(scaleAnimatorX, scaleAnimatorY, alpha);

        fabOpenSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        fabOpenSet.start();


    }

    public void initAnimations() {
        rotate_backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
    }

    private void bindFabs(ActivityDrawerBinding binding) {
        fabTrans = binding.content.fabTrans;
        fabCyclic = binding.content.fabCyclic;
        fabSaves = binding.content.fabSaves;
        fabStats = binding.content.fabStats;

        fabAddExpense = binding.content.addExpenseTrans;
        fabAddIncome = binding.content.addIncomeTrans;

        fabTrans.setOnClickListener(this);

        fabAddExpense.setOnClickListener(this);
        fabAddIncome.setOnClickListener(this);

        fabMassive = new FloatingActionButton[]{fabTrans, fabCyclic, fabSaves, fabStats};
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addExpenseTrans:
                startAddTransactionAct(false);
                break;
            case R.id.addIncomeTrans:
                startAddTransactionAct(true);
                break;
            case R.id.fabTrans:
                animateFAB();
                break;
            case R.id.fabCyclic:
                Snackbar.make(fabCyclic, "Cyclic", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.fabSaves:
                Snackbar.make(fabSaves, "Saves", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.fabStats:
                Snackbar.make(fabStats, "Stats", Snackbar.LENGTH_SHORT).show();
                break;

            default:
                throw new IllegalArgumentException("Can't recognize incoming ID");
        }
    }

    public void startAddTransactionAct(boolean isIncome) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        intent.putExtra(PLConstants.INTENT_INCOME_FLAG, isIncome);

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, fabAddIncome, "reveal");
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }


    public void animateFAB() {
        if (isTransFabOpen) {
            fabTrans.startAnimation(rotate_backward);
            fabAddIncome.startAnimation(fab_close);
            fabAddExpense.startAnimation(fab_close);
            fabAddIncome.setClickable(false);
            fabAddExpense.setClickable(false);
            isTransFabOpen = false;
        } else {
            fabTrans.startAnimation(rotate_forward);
            fabAddIncome.startAnimation(fab_open);
            fabAddExpense.startAnimation(fab_open);
            fabAddIncome.setClickable(true);
            fabAddExpense.setClickable(true);
            isTransFabOpen = true;
        }
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

    private void bindTabs(ActivityDrawerBinding binding) {
        mTabLayout = binding.content.tabs;
        mViewPager = binding.content.viewPagerContainer;

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(pageChangeListener);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void bindDrawer(ActivityDrawerBinding binding, Toolbar toolbar) {
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
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        @DrawableRes
        int[] tabsIndicators = {R.drawable.ic_menu_camera,
                R.drawable.vector_cycle, R.drawable.vector_saves, R.drawable.vector_wallet};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TransactionsFragment.newInstance();
                case 1:
                    return CycleOperationFragment.newInstance();
                case 2:
                    return CycleOperationFragment.newInstance();
                case 3:
                    return CycleOperationFragment.newInstance();
                default:
                    throw new IllegalStateException("Can't recognize which fragment I should give to");
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
                case 3:
                    return "Stats";
                default:
                    throw new IllegalStateException("Can't find title.");
            }
        }


    }

    /**
     * A {@link ViewPager.OnPageChangeListener} where fabs animations will fire
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int previousFabIdx = 0;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (currentlyPageShown[previousFabIdx]) {
                playCloseAnimation(fabMassive[previousFabIdx]);
                currentlyPageShown[previousFabIdx] = false;
            }
            currentlyPageShown[position] = true;
            playOpenAnimation(fabMassive[position]);

            Log.d("myTag", "current = " + position);
            Log.d("myTag", "prev = " + previousFabIdx);
            previousFabIdx = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
