package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.AddDebtModule;
import com.denis.mypocket.view.activity.AddDebtActivity;

import dagger.Component;

/**
 * Created by denis on 2/20/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AddDebtModule.class)
public interface AddDebtsComponent {
    void inject(AddDebtActivity activity);
}
