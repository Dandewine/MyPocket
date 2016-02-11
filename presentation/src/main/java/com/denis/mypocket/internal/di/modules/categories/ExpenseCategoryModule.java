package com.denis.mypocket.internal.di.modules.categories;

import com.denis.data.entity.mapper.ExpenseCategoryDataMapper;
import com.denis.data.local_store.categories.ExpenseCategoriesStore;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.ExpenseCategoryDataRepository;
import com.denis.data.repository.datasource.interfaces.ExpenseCategoryDataStore;
import com.denis.data.repository.datasource.local.ExpenseCategoryLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.categories.GetExpenseCategoriesUseCase;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.repository.ExpenseCategoriesRepository;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class ExpenseCategoryModule {

    @Provides @PerActivity @Named("expenseUC")
    UseCase<ExpenseCategory> getCategoriesUseCase(ThreadExecutor threadExecutor,
                                                 PostExecutionThread postExecutionThread,ExpenseCategoriesRepository repository){
        return new GetExpenseCategoriesUseCase(threadExecutor,postExecutionThread, repository);
    }

    @Provides @PerActivity
    ExpenseCategoriesRepository provideIncomeRepo(ExpenseCategoryDataMapper dataMapper, ExpenseCategoryDataStore dataStore){
        return new ExpenseCategoryDataRepository(dataMapper,dataStore);
    }

    @Provides @PerActivity ExpenseCategoryDataMapper provideMapper(){
        return new ExpenseCategoryDataMapper();
    }

    @Provides @PerActivity ExpenseCategoryDataStore provideDataStore(@Named("expenseRS") RealmStore store){
        return new ExpenseCategoryLocalDataStore(store);
    }

    @Provides @PerActivity @Named("expenseRS") RealmStore provideStore(Realm realm){
        return new ExpenseCategoriesStore(realm);
    }
}
