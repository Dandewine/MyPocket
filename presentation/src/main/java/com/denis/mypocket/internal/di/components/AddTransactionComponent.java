package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.AddTransactionModule;
import com.denis.mypocket.view.activity.AddTransactionActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {AddTransactionModule.class})
public interface AddTransactionComponent {
    void inject(AddTransactionActivity fragment);

}
