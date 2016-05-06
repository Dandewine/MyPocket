package com.denis.mypocket.internal.di.modules;

//@Module
public class GetCycleOperationViewModelModule {
/*

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
*/


}
