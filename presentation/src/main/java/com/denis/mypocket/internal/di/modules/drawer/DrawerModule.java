package com.denis.mypocket.internal.di.modules.drawer;

import android.content.Context;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.ActivityModule;
import com.denis.mypocket.internal.di.modules.wallets.WalletFromLocalModule;
import com.denis.mypocket.screens.tab_with_drawer_screen.viewmodel.DrawerNavViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 4/29/16.
 */

@Module(includes = {ActivityModule.class, DeleteUserUseCaseModule.class,LogoutUseCaseModule.class, WalletFromLocalModule.class})
public class DrawerModule {

    @Provides @PerActivity
    DrawerNavViewModel provideViewModel (@Named("logout") UseCase logoutUseCase,
                                         @Named("delete_user") UseCase deleteUser,
                                         @Named("delete_token") UseCase deleteTokenUseCase,
                                         @Named("user_get_local") UseCase<User> getUserUseCase,
                                         UseCase<Wallet> walletsUseCase,
                                         @Named("activity") Context context) {
        return new DrawerNavViewModel(logoutUseCase, deleteUser, deleteTokenUseCase, getUserUseCase, walletsUseCase, context);
    }
}
