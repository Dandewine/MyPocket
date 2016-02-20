package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ActivityAddDebtBinding;
import com.denis.mypocket.internal.di.components.AddDebtsComponent;
import com.denis.mypocket.viewmodel.adding.AddDebtViewModel;

import javax.inject.Inject;

/**
 * Created by denis on 2/20/16.
 */
public class AddDebtActivity extends BaseActivity {

    @Inject public AddDebtViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddDebtBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_debt);
        Log.d("myTag","viewModel is null = "+(viewModel == null));
        dataBinding.setViewModel(viewModel);
    }

    @Override
    protected void initDIComponent() {
        //AddDebtsComponent
    }
}
