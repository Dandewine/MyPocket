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



public class AddCycleOperationActivity extends BaseActivity {

  //  @Inject public AddCycleOperationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  binding = DataBindingUtil.setContentView(this, R.layout.activity_add_cycle_operation);
       // setupEnterTransition();
       // setupExitTransition();
       // configireToolbar(binding.toolbarAddCO.toolbar,R.string.toolbar_add_co,true);
       // binding.radioGroupAddCycleOP.setOnCheckedChangeListener(viewModel.changeListener);
        //binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
      /*  DaggerAddCycleOPComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build().inject(this);*/
    }








}
