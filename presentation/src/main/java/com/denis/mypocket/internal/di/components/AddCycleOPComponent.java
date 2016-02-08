package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.broadcast.CycleOperationBroadcast;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.AddCycleOPModule;
import com.denis.mypocket.view.activity.AddCycleOperationActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AddCycleOPModule.class)
public interface AddCycleOPComponent {
    void inject(AddCycleOperationActivity activity);
    void inject(CycleOperationBroadcast broadcast);
}
