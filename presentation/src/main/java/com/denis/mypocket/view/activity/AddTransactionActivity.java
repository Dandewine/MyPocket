package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Debug;
import android.view.MenuItem;

import com.denis.mypocket.PLConstants;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddTransactionBinding;
import com.denis.mypocket.internal.di.components.DaggerAddTransactionComponent;
import com.denis.mypocket.internal.di.modules.ProvideViewModelAddTransactionModule;
import com.denis.mypocket.viewmodel.adding.AddTransactionViewModel;

import javax.inject.Inject;

import dagger.Lazy;


public class AddTransactionActivity extends BaseActivity {

    @Inject
    public AddTransactionViewModel viewModel;
    private ActivityAddTransactionBinding binding;
    private boolean isIncome = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        isIncome = getIntent().getBooleanExtra(PLConstants.INTENT_INCOME_FLAG, false);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_transaction);
        binding.setViewModel(viewModel);

        configireToolbar(binding.toolbarAddTrans.toolbar, R.string.toolbar_add_trans, true);
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
