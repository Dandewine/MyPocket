package com.denis.mypocket.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.denis.data.entity.mapper.CycleOperationDataMapper;
import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.local_store.CircleOperationRealmStore;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.CycleOperationDataRepository;
import com.denis.data.repository.datasource.interfaces.CycleOperationDataStore;
import com.denis.data.repository.datasource.local.CycleOperationLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.cycle_operations.AddCircleOperationUseCase;
import com.denis.domain.repository.CycleOperationRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.adding.AddCycleOperationViewModel;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(includes = WalletModule.class)
public class AddCycleOPModule {

    public AddCycleOPModule() {
        Log.d(PLTags.INSTANCE_TAG,"AddCycleOpModule created "+hashCode());
    }

    @Provides
    @PerActivity
    RealmStore provideRealmStore(Realm realm) {
        return new CircleOperationRealmStore(realm);
    }

    @Provides
    @PerActivity
    CycleOperationDataStore provideDataStore(CircleOperationRealmStore realmStore) {
        return new CycleOperationLocalDataStore(realmStore);
    }

    @Provides
    @PerActivity
    TransactionDataMapper provideTransactionDataMapper(WalletDataMapper dataMapper) {
        return new TransactionDataMapper(dataMapper);
    }

    @Provides
    @PerActivity
    CycleOperationDataMapper provideDataMapper(TransactionDataMapper dataMapper) {
        return new CycleOperationDataMapper(dataMapper);
    }

    @Provides
    @PerActivity
    CycleOperationRepository provideCycleOPRepository(CycleOperationDataStore dataStore, CycleOperationDataMapper dataMapper) {
        return new CycleOperationDataRepository(dataStore, dataMapper);
    }

    @Provides
    @PerActivity
    UseCase provideAddUseCase(ThreadExecutor executor,
                              PostExecutionThread thread,
                              CycleOperationRepository repository, Context context) {
        return new AddCircleOperationUseCase(executor, thread, repository, context);
    }

    @Provides
    @PerActivity
    AddCycleOperationViewModel provideViewModel(UseCase addCycleOperationUseCase, Context context) {
        return new AddCycleOperationViewModel(addCycleOperationUseCase, context);
    }

}
