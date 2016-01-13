package com.denis.domain.interactor;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;

import javax.inject.Inject;

import rx.Observable;

public class AddWalletUseCase extends UseCase<Wallet> {

    private WalletRepository walletRepository;

    @Inject
    public AddWalletUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                            WalletRepository walletRepository) {
        super(threadExecutor, postExecutionThread);
        this.walletRepository = walletRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Wallet... arg) {
        return walletRepository.addWallet(arg[0]);
    }
}
