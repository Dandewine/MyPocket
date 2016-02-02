package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddCycleOperationBinding;
import com.denis.mypocket.internal.di.components.DaggerAddCycleOPComponent;
import com.denis.mypocket.internal.di.modules.AddCycleOPModule;
import com.denis.mypocket.viewmodel.adding.AddCycleOperationViewModel;

import javax.inject.Inject;

public class AddCycleOperationActivity extends BaseActivity {

    @Inject
    public AddCycleOperationViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddCycleOperationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_cycle_operation);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        DaggerAddCycleOPComponent.builder()
                .applicationComponent(getApplicationComponent())
                .addCycleOPModule(new AddCycleOPModule())
                .build().inject(this);
    }
}
