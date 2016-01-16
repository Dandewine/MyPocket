package com.denis.mypocket.view.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.denis.mypocket.GUIUtils;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddTransactionBinding;
import com.denis.mypocket.internal.di.components.AddTransactionComponent;
import com.denis.mypocket.internal.di.components.DaggerAddTransactionComponent;
import com.denis.mypocket.internal.di.modules.AddTransactionModule;
import com.denis.mypocket.view.OnRevealListener;
import com.denis.mypocket.viewmodel.AddTransactionViewModel;

import javax.inject.Inject;


public class AddTransactionActivity extends BaseActivity {

    @Inject
    public AddTransactionViewModel viewModel;
    private AddTransactionComponent component;
    private ActivityAddTransactionBinding binding;

    public AddTransactionActivity() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_add_transaction);
        binding.setViewModel(viewModel);
        binding.addTrans.setOnClickListener(viewModel.addOnClick);
        configireToolbar(binding.toolbarAddTrans.toolbar,R.string.toolbar_add_trans,true);
        setupExitAnimation();

    }

    private void initComponent(){
        component = DaggerAddTransactionComponent.builder()
                .applicationComponent(getApplicationComponent())
                .addTransactionModule(new AddTransactionModule())
                .build();
        component.inject(this);
    }

  /*  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation(){
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion)
                .setDuration(200);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow(binding.toolbarAddTrans.toolbar);
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
    }*/

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fade = new Fade();
        fade.setDuration(300L);
        fade.setInterpolator(new LinearInterpolator());
        getWindow().setReturnTransition(fade);
        getWindow().setEnterTransition(fade);

    }


    private void animateRevealShow(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        GUIUtils.animateRvealShow(this, binding.toolbarAddTrans.toolbar, binding.toolbarAddTrans.toolbar.getWidth() / 2, R.color.fab_income,
                cx, cy, new OnRevealListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }


    @Override
    public void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
