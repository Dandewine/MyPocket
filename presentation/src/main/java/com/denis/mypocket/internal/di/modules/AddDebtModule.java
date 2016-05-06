package com.denis.mypocket.internal.di.modules;

/**
 * Created by denis on 2/20/16.
 */

/*@Module*/
public class AddDebtModule {

   /* @Provides @PerActivity
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
    }*/
}
