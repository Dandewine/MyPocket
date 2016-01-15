package com.denis.mypocket.internal.di.components;

import android.content.Context;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.mypocket.internal.di.modules.ApplicationModule;
import com.denis.mypocket.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    Realm realm();
}
