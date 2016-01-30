package com.denis.mypocket.internal.di.modules;

import com.denis.data.entity.mapper.IncomeCategoryDataMapper;
import com.denis.data.local_store.IncomeCategoriesStore;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.IncomeCategoriesDataRepository;
import com.denis.data.repository.datasource.interfaces.IncomeCategoryDataStore;
import com.denis.data.repository.datasource.local.IncomeCateforyLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.categories.GetIncomeCategoriesUseCase;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.repository.IncomeCategoriesRepository;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class IncomeCategoryModule {

    @Provides @PerActivity @Named("incomeUC")
    UseCase<IncomeCategory> getCategoriesUseCase(ThreadExecutor threadExecutor,
                                                 PostExecutionThread postExecutionThread, IncomeCategoriesRepository repository){
        return new GetIncomeCategoriesUseCase(threadExecutor,postExecutionThread, repository);
    }

    @Provides @PerActivity IncomeCategoriesRepository provideIncomeRepo(IncomeCategoryDataMapper dataMapper, IncomeCategoryDataStore dataStore){
        return new IncomeCategoriesDataRepository(dataMapper,dataStore);
    }

    @Provides @PerActivity IncomeCategoryDataMapper provideMapper(){
        return new IncomeCategoryDataMapper();
    }

    @Provides @PerActivity IncomeCategoryDataStore provideDataStore(@Named("incomeRS") RealmStore store){
        return new IncomeCateforyLocalDataStore(store);
    }

    @Provides @PerActivity @Named("incomeRS") RealmStore provideStore(Realm realm){
        return new IncomeCategoriesStore(realm);
    }
}
