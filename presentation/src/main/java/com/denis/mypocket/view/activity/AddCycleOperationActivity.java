package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;

import com.denis.mypocket.AnimationUtils;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddCycleOperationBinding;
import com.denis.mypocket.internal.di.components.DaggerAddCycleOPComponent;
import com.denis.mypocket.viewmodel.adding.AddCycleOperationViewModel;

import javax.inject.Inject;

public class AddCycleOperationActivity extends BaseActivity {

    @Inject
    public AddCycleOperationViewModel viewModel;
    private ActivityAddCycleOperationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_cycle_operation);
       // setupEnterTransition();
       // setupExitTransition();
        configireToolbar(binding.toolbarAddCO.toolbar,R.string.toolbar_add_co,true);
        binding.radioGroupAddCycleOP.setOnCheckedChangeListener(viewModel.changeListener);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        DaggerAddCycleOPComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupEnterTransition(){
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow(binding.cardView);

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

    private void setupExitTransition(){
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(500);
    }

    @Override
    public void onBackPressed() {
        AnimationUtils.animateRevealHide(this,  binding.cardView , R.color.colorAccent, binding.activityDetailsVwCircle.getWidth() / 2,
                new AnimationUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        binding.activityDetailsVwCircle.setVisibility(View.VISIBLE);
                        AddCycleOperationActivity.super.onBackPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    private void animateRevealShow(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        AnimationUtils.animateRevealShow(this, binding.cardView , binding.activityDetailsVwCircle.getWidth() / 2, R.color.colorAccent,
                cx, cy, new AnimationUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }




}
