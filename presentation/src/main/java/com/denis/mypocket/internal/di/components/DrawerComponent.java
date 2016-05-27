package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.DrawerModule;
import com.denis.mypocket.screens.tab_with_drawer_screen.view.DrawerActivity;

import dagger.Component;

/**
 * Created by denis on 4/29/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = DrawerModule.class)
public interface DrawerComponent extends ActivityComponent {
    void inject(DrawerActivity activity);
}
