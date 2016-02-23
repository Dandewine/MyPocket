package com.denis.mypocket.internal.di.components.fragment;

import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.modules.FragmentModule;
import com.denis.mypocket.view.fragments.BaseFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment baseFragment);
}
