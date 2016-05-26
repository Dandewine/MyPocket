package com.denis.domain.interactor.wallets;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by denis on 5/23/16.
 */

public class SaveWalletList extends UseCase<List<Wallet>> {

    private WalletRepository walletRepository;

    @Inject
    public SaveWalletList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                          WalletRepository walletRepository) {
        super(threadExecutor, postExecutionThread);
        this.walletRepository = walletRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(List<Wallet>... arg) {
        return walletRepository.addWallet(arg[0]);
    }


}
