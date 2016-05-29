package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.transactions.TransactionModule;
import com.denis.mypocket.screens.add_transaction_screen.view.AddTransactionActivity;

import dagger.Component;

/**
 * @author denis at 5/28/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = TransactionModule.class)
public interface TransactionComponent {
    void inject(AddTransactionActivity activity);
}
