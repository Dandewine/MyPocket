package com.denis.mypocket.internal.di.components.activity;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.ProvideViewModelAddTransactionModule;
import com.denis.mypocket.view.activity.AddTransactionActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
           modules = ProvideViewModelAddTransactionModule.class)
public interface AddTransactionComponent {
    void inject(AddTransactionActivity fragment);
}
