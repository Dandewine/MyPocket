package com.denis.mypocket.internal.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.denis.domain.models.CycleOperation;
import com.denis.mypocket.MyPocketApp;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.components.DaggerAddCycleOPComponent;
import com.denis.mypocket.internal.di.modules.AddCycleOPModule;
import com.denis.mypocket.internal.di.modules.WalletModule;
import com.denis.mypocket.viewmodel.adding.AddCycleOperationViewModel;

import javax.inject.Inject;

/**
 * Will create a new ViewModel instance, even if we already has existing one
 */
public class CycleOperationBroadcast extends BroadcastReceiver {

    @Inject public AddCycleOperationViewModel viewModel;

    @Override
    public void onReceive(Context context, Intent intent) {
        initDI();
        CycleOperation operation = intent.getParcelableExtra("data");
        viewModel.execute(operation);
    }

    private void initDI(){
        ApplicationComponent component = MyPocketApp.getPocketApp().getApplicationComponent();
        DaggerAddCycleOPComponent.builder()
                .applicationComponent(component)
                .addCycleOPModule(new AddCycleOPModule())
                .walletModule(new WalletModule())
                .build().inject(this);
    }
}
