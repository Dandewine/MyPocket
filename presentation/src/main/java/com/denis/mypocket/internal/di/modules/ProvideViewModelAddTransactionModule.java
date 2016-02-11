package com.denis.mypocket.internal.di.modules;

import android.content.Context;

import com.denis.domain.interactor.AddTransactionUseCasesFacade;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.adding.AddTransactionViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public class ProvideViewModelAddTransactionModule extends AddTransactionModule{

    private boolean isIncome;

    public ProvideViewModelAddTransactionModule(boolean isIncome) {
        this.isIncome = isIncome;
    }

    @Provides @PerActivity
    AddTransactionViewModel provideAddTransactionViewModel(AddTransactionUseCasesFacade facade,
                                                           @Named("activity") Context context){
        return new AddTransactionViewModel(facade,context,isIncome);
    }
}
