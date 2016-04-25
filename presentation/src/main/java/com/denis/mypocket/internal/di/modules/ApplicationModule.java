package com.denis.mypocket.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.denis.data.entity.Token;
import com.denis.data.executor.JobExecutor;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.TokenRealmStore;
import com.denis.data.repository.TokenDataRepository;
import com.denis.data.repository.datasource.interfaces.TokenDataStore;
import com.denis.data.repository.datasource.local.TokenLocalDataStore;
import com.denis.data.rest.RestClientRetrofit;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.auth.TokenGet;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.TokenRepository;
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
    UseCase<String> provideTokenUseCase(ThreadExecutor executor, PostExecutionThread thread, TokenRepository repository){
        return new TokenGet(executor,thread,repository);
    }

    @Provides @Singleton TokenRepository provideRepo(TokenDataStore dataStore){
        return new TokenDataRepository(dataStore);
    }

    @Provides @Singleton TokenDataStore provideDataStore(RealmStore<Token> store){
        return new TokenLocalDataStore(store);
    }

    @Provides @Singleton RealmStore<Token> provideLocalStore(Realm realm){
        return new TokenRealmStore(realm);
    }

    @Provides @Singleton
    RestClient provideRestClient(UseCase<String> tokenGetUseCase){
        return new RestClientRetrofit(tokenGetUseCase);
    }



}
