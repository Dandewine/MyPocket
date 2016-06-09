package com.denis.mypocket.internal.di.components;

import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.internal.di.modules.FragmentModule;
import com.denis.mypocket.internal.di.modules.transactions.TransactionTabModule;
import com.denis.mypocket.screens.transactions_tab_screen.view.TransactionsFragment;

import dagger.Component;

/**
 * @author Denis_Zinkovskiy at 6/6/16.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {TransactionTabModule.class, FragmentModule.class})
public interface TransactionTabComponent extends FragmentComponent {
    void inject(TransactionsFragment fragment);
}
