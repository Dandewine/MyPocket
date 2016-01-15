package com.denis.mypocket.internal.di.modules;

import android.content.Context;

import com.denis.data.executor.JobExecutor;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.mypocket.MyPocketApp;
import com.denis.mypocket.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class ApplicationModule {
    private final MyPocketApp pocketApp;

    public ApplicationModule(MyPocketApp pocketApp) {
        this.pocketApp = pocketApp;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.pocketApp;
    }


    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton Realm provideRealm(){
        return Realm.getDefaultInstance();
    }

}
