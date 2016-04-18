package com.denis.mypocket.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.denis.data.executor.JobExecutor;
import com.denis.data.rest.RestClientRetrofit;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.mypocket.MyPocketApp;
import com.denis.mypocket.UIThread;
import com.denis.mypocket.utils.PLTags;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class ApplicationModule {
    private final MyPocketApp pocketApp;

    public ApplicationModule(MyPocketApp pocketApp) {
        this.pocketApp = pocketApp;
        Log.d(PLTags.INSTANCE_TAG,"AppModule, "+hashCode());
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

    @Provides @Singleton
    RestClient provideRestClient(){
        return new RestClientRetrofit();
    }



}
