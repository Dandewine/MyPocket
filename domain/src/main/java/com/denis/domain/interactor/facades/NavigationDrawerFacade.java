package com.denis.domain.interactor.facades;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author Denis_Zinkovskiy at 5/30/16.
 */

public class NavigationDrawerFacade {
    private UseCase logoutUseCase;
    private UseCase deleteUser;
    private UseCase deleteTokenUseCase;
    private UseCase<Wallet> walletsUseCase;
    private UseCase<Wallet> updateWalletUseCase;

    @Inject
    public NavigationDrawerFacade(UseCase logoutUseCase,
                                  UseCase deleteUser,
                                  UseCase deleteTokenUseCase,
                                  UseCase<Wallet> walletsUseCase,
                                  UseCase<Wallet> updateWalletUseCase) {
        this.logoutUseCase = logoutUseCase;
        this.deleteUser = deleteUser;
        this.deleteTokenUseCase = deleteTokenUseCase;
        this.walletsUseCase = walletsUseCase;
        this.updateWalletUseCase = updateWalletUseCase;
    }

    public void logout(Subscriber subscriber){
        logoutUseCase.executeAsync(subscriber);
    }

    public void deleteUser(Subscriber subscriber){
        deleteUser.executeSync(subscriber);
    }

    public void deleteToken(Subscriber subscriber){
        deleteTokenUseCase.executeSync(subscriber);
    }

    public void retrieveAllWallets(Subscriber subscriber){
        walletsUseCase.executeAsync(subscriber);
    }

    public void switchWallet(Subscriber subscriber, Wallet wallet){
        updateWalletUseCase.executeSync(subscriber, wallet);
    }

    public void unSubscribe(){
        logoutUseCase.unSubscribe();
        deleteUser.unSubscribe();
        deleteTokenUseCase.unSubscribe();
        walletsUseCase.unSubscribe();
        updateWalletUseCase.unSubscribe();
    }
}
