package com.denis.mypocket.view.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.AnimationUtils;
import com.denis.mypocket.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddTransactionBinding;
import com.denis.mypocket.internal.di.components.activity.DaggerAddTransactionComponent;
import com.denis.mypocket.internal.di.modules.ProvideViewModelAddTransactionModule;
import com.denis.mypocket.utils.GUIUtils;
import com.denis.mypocket.viewmodel.adding.AddTransactionViewModel;

import javax.inject.Inject;

import dagger.Lazy;


public class AddTransactionActivity extends BaseActivity {

    @Inject
    public AddTransactionViewModel viewModel;
    private ActivityAddTransactionBinding binding;
    private boolean isIncome = false;
    private ViewGroup mRlContainer;
    private FloatingActionButton mFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        isIncome = getIntent().getBooleanExtra(PLConstants.INTENT_INCOME_FLAG, false);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_transaction);
        binding.setViewModel(viewModel);
        mRlContainer = binding.containerAddTrans;
        //mFab = binding.addTrans;
       // configireToolbar(binding.toolbarAddTrans.toolbar, R.string.toolbar_add_trans, true);
        //setupEnterAnimation();
        //setupExitAnimation();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow(mRlContainer);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void animateRevealShow(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        AnimationUtils.animateRevealShow(this, mRlContainer, mFab.getWidth() / 2, R.color.fab_income,
                cx, cy, new AnimationUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        //initViews();
                    }
                });
    }

    @Override
    public void onBackPressed() {
      /*  AnimationUtils.animateRevealHide(this, mRlContainer, R.color.fab_income, mFab.getWidth() / 2,
                new AnimationUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        AddTransactionActivity.super.onBackPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });*/
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(500);
    }

    @Override
    protected void initDIComponent() {
        DaggerAddTransactionComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .provideViewModelAddTransactionModule(new ProvideViewModelAddTransactionModule(isIncome))
                .build().inject(this);
    }


    @Override
    public void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
