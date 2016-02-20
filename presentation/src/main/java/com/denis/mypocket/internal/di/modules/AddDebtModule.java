package com.denis.mypocket.internal.di.modules;

import com.denis.data.entity.mapper.DebtsMapper;
import com.denis.data.local_store.DebtRealmStore;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.DebtsRepository;
import com.denis.data.repository.datasource.interfaces.DebtsDataStore;
import com.denis.data.repository.datasource.local.DebtsOperationLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.AddDebtUseCase;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.DebtRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.adding.AddDebtViewModel;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by denis on 2/20/16.
 */

@Module
public class AddDebtModule {

    @Provides @PerActivity
    AddDebtViewModel provideAddDebtViewModel(UseCase useCase){
        return new AddDebtViewModel(useCase);
    }

    @Provides @PerActivity
    UseCase provideAddUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, DebtRepository debtRepository){
        return new AddDebtUseCase(threadExecutor, postExecutionThread, debtRepository);
    }

    @Provides @PerActivity DebtRepository provideRepository(DebtsMapper mapper, DebtsDataStore dataStore){
        return new DebtsRepository(mapper,dataStore);
    }

    @Provides @PerActivity DebtsMapper provideMapper(){
        return new DebtsMapper();
    }

    @Provides @PerActivity DebtsDataStore provideDebtsDataStore(RealmStore realmStore){
        return new DebtsOperationLocalDataStore(realmStore);
    }

    @Provides @PerActivity RealmStore provideReamlStore(Realm realm){
        return new DebtRealmStore(realm);
    }
}
