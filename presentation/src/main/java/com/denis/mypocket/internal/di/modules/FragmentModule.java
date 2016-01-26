package com.denis.mypocket.internal.di.modules;

import android.app.Fragment;
import android.util.Log;

import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.utils.PLTags;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
        Log.d(PLTags.INSTANCE_TAG,"Fragment Module, "+hashCode());
    }

    @Provides @PerFragment Fragment fragment(){
        return fragment;
    }
}
