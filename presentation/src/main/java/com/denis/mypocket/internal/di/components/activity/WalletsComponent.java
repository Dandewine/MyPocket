package com.denis.mypocket.internal.di.components.activity;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.AddWalletModule;
import com.denis.mypocket.view.activity.AddWalletActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = AddWalletModule.class)
public interface WalletsComponent extends ActivityComponent {
    void inject(AddWalletActivity activity);
}
