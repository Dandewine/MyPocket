package com.denis.mypocket.view.add_wallet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddWalletBinding;
import com.denis.mypocket.internal.di.components.DaggerWalletsComponent;
import com.denis.mypocket.internal.di.modules.AddWalletModule;
import com.denis.mypocket.view.activity.BaseActivity;
import com.denis.mypocket.viewmodel.adding.WalletsViewModel;

import javax.inject.Inject;

public class WalletActivity extends BaseActivity {

    @Inject
    public WalletsViewModel viewModel;
    private boolean isAddPressed = false;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, WalletActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddWalletBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_wallet);
        configireToolbar(binding.include.toolbar, R.string.toolbar_add_wallet, true);
        binding.fabAAW.setOnClickListener(v -> {

            if(!isAddPressed){
                binding.llEditTextHolder.setVisibility(View.VISIBLE);
                revealEditText(binding.llEditTextHolder);
            }else{
                hideEditText(binding.llEditTextHolder);
            }

            binding.fabAAW.morphTo();
        });


        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void revealEditText(ViewGroup view) {
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        isAddPressed = true;
        anim.start();
    }

    private void hideEditText(ViewGroup view) {
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int initialRadius = view.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        isAddPressed = false;
        anim.start();
    }

    @Override
    protected void initDIComponent() {
        DaggerWalletsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .addWalletModule(new AddWalletModule())
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }
}
