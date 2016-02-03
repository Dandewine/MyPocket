package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

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

        configireToolbar(binding.toolbarAddCO.toolbar,R.string.toolbar_add_co,true);

        binding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        DaggerAddCycleOPComponent.builder()
                .applicationComponent(getApplicationComponent())
                .addCycleOPModule(new AddCycleOPModule())
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
}
