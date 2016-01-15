package com.denis.mypocket.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentAddTransactionBinding;
import com.denis.mypocket.internal.di.components.AddTransactionComponent;
import com.denis.mypocket.internal.di.components.DaggerAddTransactionComponent;
import com.denis.mypocket.internal.di.modules.AddTransactionModule;
import com.denis.mypocket.viewmodel.AddTransactionViewModel;

import javax.inject.Inject;


public class AddTransactionActivity extends BaseActivity {

    @Inject
    public AddTransactionViewModel viewModel;
    private AddTransactionComponent component;

    public AddTransactionActivity() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        FragmentAddTransactionBinding binding =  DataBindingUtil.setContentView(this,R.layout.fragment_add_transaction);
        binding.setViewModel(viewModel);
        binding.addTrans.setOnClickListener(viewModel.addOnClick);
        configireToolbar(binding.toolbarAddTrans.toolbar,R.string.toolbar_add_trans,true);
    }

    private void initComponent(){
        component = DaggerAddTransactionComponent.builder()
                .applicationComponent(getApplicationComponent())
                .addTransactionModule(new AddTransactionModule())
                .build();
        component.inject(this);
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
