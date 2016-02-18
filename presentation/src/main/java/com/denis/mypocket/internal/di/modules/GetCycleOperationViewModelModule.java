package com.denis.mypocket.internal.di.modules;

import android.util.Log;

import com.denis.data.entity.mapper.CycleOperationDataMapper;
import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.local_store.CircleOperationRealmStore;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.CycleOperationDataRepository;
import com.denis.data.repository.datasource.interfaces.CycleOperationDataStore;
import com.denis.data.repository.datasource.local.CycleOperationLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.cycle_operations.GetAllCycleOperationUseCase;
import com.denis.domain.repository.CycleOperationRepository;
import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.model.mapper.CycleOperationModelMapper;
import com.denis.mypocket.model.mapper.TransactionModelDataMapper;
import com.denis.mypocket.model.mapper.WalletModelDataMapper;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.getting.GetCycleOperationViewModel;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class GetCycleOperationViewModelModule {

    public GetCycleOperationViewModelModule() {
        Log.d(PLTags.INSTANCE_TAG,"get cycle operation module "+hashCode());
    }

    @Provides @PerFragment RealmStore provideRealmStore(Realm realm) {
        return new CircleOperationRealmStore(realm);
    }

    @Provides @PerFragment CycleOperationDataStore provideDataStore(CircleOperationRealmStore realmStore) {
        return new CycleOperationLocalDataStore(realmStore);
    }

    @Provides @PerFragment CycleOperationDataMapper provideDataMapper(TransactionDataMapper dataMapper) {
        return new CycleOperationDataMapper(dataMapper);
    }

    @Provides @PerFragment CycleOperationRepository provideCycleOPRepository(CycleOperationDataStore dataStore, CycleOperationDataMapper dataMapper) {
        return new CycleOperationDataRepository(dataStore, dataMapper);
    }

    @Provides @PerFragment UseCase provideAddUseCase(ThreadExecutor executor,
                              PostExecutionThread thread,
                              CycleOperationRepository repository) {
        return new GetAllCycleOperationUseCase(executor, thread, repository);
    }

    @Provides @PerFragment WalletModelDataMapper provideWalletModelDataMapper(){
        return new WalletModelDataMapper();
    }


    @Provides @PerFragment TransactionModelDataMapper provideModelDataMapper(WalletModelDataMapper dataMapper){
        return new TransactionModelDataMapper(dataMapper);
    }

    @Provides @PerFragment
    CycleOperationModelMapper provideMapper(TransactionModelDataMapper mapper){
        return new CycleOperationModelMapper(mapper);
    }

    @Provides @PerFragment GetCycleOperationViewModel provideViewModel(UseCase useCase, CycleOperationModelMapper modelMapper){
        return new GetCycleOperationViewModel(useCase, modelMapper);
    }


}
