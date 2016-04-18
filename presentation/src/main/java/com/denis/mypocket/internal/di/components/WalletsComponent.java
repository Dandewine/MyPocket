package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.ActivityModule;
import com.denis.mypocket.internal.di.modules.AddWalletModule;
import com.denis.mypocket.view.activity.AddWalletActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, AddWalletModule.class})
public interface WalletsComponent extends ActivityComponent {
    void inject(AddWalletActivity activity);
}
