package com.denis.mypocket.internal.di.components;

import android.app.Activity;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.denis.mypocket.internal.di.PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();


}
