package com.denis.domain.interactor.facades;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author Denis_Zinkovskiy at 5/30/16.
 */

public class NavigationDrawerFacade {
    private UseCase logoutUseCase;
    private UseCase deleteUser;
    private UseCase deleteTokenUseCase;
    private UseCase<User> retrieveUser;
    private UseCase<Wallet> walletsUseCase;
    private UseCase<List<Wallet>> updateWalletUseCase;

    @Inject public NavigationDrawerFacade(UseCase logoutUseCase,
                                  UseCase deleteUser,
                                  UseCase deleteTokenUseCase,
                                  UseCase<Wallet> walletsUseCase,
                                  UseCase<List<Wallet>> updateWalletUseCase, UseCase<User> retrieveUserUseCase) {

        this.logoutUseCase = logoutUseCase;
        this.deleteUser = deleteUser;
        this.deleteTokenUseCase = deleteTokenUseCase;
        this.walletsUseCase = walletsUseCase;
        this.updateWalletUseCase = updateWalletUseCase;
        this.retrieveUser = retrieveUserUseCase;
    }

    public void logout(Subscriber<Integer> subscriber){
        logoutUseCase.executeAsync(subscriber);
    }

    public void deleteUser(Subscriber<Boolean> subscriber){
        deleteUser.executeSync(subscriber);
    }

    public void deleteToken(Subscriber<Boolean> subscriber){
        deleteTokenUseCase.executeSync(subscriber);
    }

    public void retrieveAllWallets(Subscriber<List<Wallet>> subscriber){
        walletsUseCase.executeSync(subscriber);
    }

    public void updateWallets(Subscriber<List<Wallet>> subscriber, List<Wallet> wallets){
        updateWalletUseCase.executeSync(subscriber, wallets);
    }

    public void retriveUser(Subscriber<List<User>> subscriber){
        retrieveUser.executeSync(subscriber);
    }

    public void unSubscribe(){
        logoutUseCase.unSubscribe();
        deleteUser.unSubscribe();
        deleteTokenUseCase.unSubscribe();
        walletsUseCase.unSubscribe();
        updateWalletUseCase.unSubscribe();
    }
}
