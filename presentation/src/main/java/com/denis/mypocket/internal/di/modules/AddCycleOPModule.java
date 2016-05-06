package com.denis.mypocket.internal.di.modules;

/*@Module(includes = AddTransactionModule.class)*/
public class AddCycleOPModule {
/*
    public AddCycleOPModule() {
        Log.d(PLTags.INSTANCE_TAG,"AddCycleOpModule created "+hashCode());
    }

    @Provides @PerActivity
    RealmStore provideRealmStore(Realm realm) {
        return new CircleOperationRealmStore(realm);
    }

    @Provides @PerActivity
    CycleOperationDataStore provideDataStore(CircleOperationRealmStore realmStore) {
        return new CycleOperationLocalDataStore(realmStore);
    }

    @Provides @PerActivity
    CycleOperationDataMapper provideDataMapper(TransactionDataMapper dataMapper) {
        return new CycleOperationDataMapper(dataMapper);
    }

    @Provides @PerActivity
    CycleOperationRepository provideCycleOPRepository(CycleOperationDataStore dataStore, CycleOperationDataMapper dataMapper) {
        return new CycleOperationDataRepository(dataStore, dataMapper);
    }

    @Provides @PerActivity
    UseCase provideAddUseCase(ThreadExecutor executor,
                              PostExecutionThread thread,
                              CycleOperationRepository repository, AddTransactionUseCase useCase) {
        return new AddCircleOperationUseCase(executor, thread, repository, useCase);
    }

    @Provides @PerActivity
    AddCycleOperationViewModel provideViewModel(UseCase addCycleOperationUseCase,
                                                @Named("getWallets") UseCase<Wallet> walletUseCase,
                                                @Named("getTransactions") UseCase<Transaction> transactionUseCase,
                                                @Named("activity") Context context, TransactionModelDataMapper dataMapper) {
        return new AddCycleOperationViewModel(addCycleOperationUseCase,transactionUseCase,walletUseCase,context,dataMapper);
    }*/

}
