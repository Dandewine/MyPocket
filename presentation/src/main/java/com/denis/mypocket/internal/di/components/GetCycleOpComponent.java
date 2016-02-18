package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.internal.di.modules.GetCycleOperationViewModelModule;
import com.denis.mypocket.view.fragments.CycleOperationFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = GetCycleOperationViewModelModule.class)
public interface GetCycleOpComponent {
    void inject(CycleOperationFragment fragment);
}
