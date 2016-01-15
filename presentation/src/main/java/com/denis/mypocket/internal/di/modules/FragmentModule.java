package com.denis.mypocket.internal.di.modules;

import android.app.Fragment;

import com.denis.mypocket.internal.di.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides @PerFragment Fragment fragment(){
        return fragment;
    }
}
