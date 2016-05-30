package com.denis.mypocket.internal.di.components.fragment;

import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.WalletGetModule;
import com.denis.mypocket.view.fragments.WalletFragment;

import dagger.Component;

@PerFragment
@Component(modules = WalletGetModule.class, dependencies = ApplicationComponent.class)
public interface WalletFragComponent extends FragmentComponent{
    void inject(WalletFragment fragment);
}
