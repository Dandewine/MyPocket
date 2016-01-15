package com.denis.mypocket.internal.di.modules;

import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.TransactionRealmStore;
import com.denis.data.repository.TransactionDataRepository;
import com.denis.data.repository.datasource.TransactionDataStore;
import com.denis.data.repository.datasource.TransactionLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.AddTransactionUseCase;
import com.denis.domain.repository.TransactionRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.AddTransactionViewModel;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class AddTransactionModule {

    @Provides @PerActivity
    AddTransactionViewModel provideAddTransactionViewModel(AddTransactionUseCase addTransactionUseCase){
        return new AddTransactionViewModel(addTransactionUseCase);
    }

    @Provides @PerActivity
    AddTransactionUseCase provideAddTransactionUseCase(ThreadExecutor threadExecutor,
                                                       PostExecutionThread postExecutionThread,
                                                       TransactionRepository repository){
        return new AddTransactionUseCase(threadExecutor,postExecutionThread,repository);
    }

    @Provides @PerActivity RealmStore provideTransactionEntityRealmStore(Realm realm){
        return new TransactionRealmStore(realm);
    }

    @Provides @PerActivity TransactionDataStore provideTransactionDataStore(RealmStore store){
        return new TransactionLocalDataStore(store);
    }

    @Provides @PerActivity TransactionDataMapper provideTransactionDataMapper(){
        return new TransactionDataMapper();
    }

    @Provides @PerActivity TransactionRepository provideTransactionRepository(TransactionDataStore dataStore, TransactionDataMapper dataMapper){
        return new TransactionDataRepository(dataStore,dataMapper);
    }

}
