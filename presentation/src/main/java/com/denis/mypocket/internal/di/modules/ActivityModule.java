package com.denis.mypocket.internal.di.modules;

import android.app.Activity;
import android.util.Log;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
        Log.d(PLTags.INSTANCE_TAG,"Activity Module, "+hashCode());
    }


    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides @PerActivity public Activity getActivity() {return activity;}


}
