package com.denis.mypocket.internal.di.components;


import android.support.v4.app.Fragment;

import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.internal.di.modules.FragmentModule;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment fragment();
}
