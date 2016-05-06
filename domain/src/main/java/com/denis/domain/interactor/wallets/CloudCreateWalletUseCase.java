package com.denis.domain.interactor.wallets;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;

import rx.Observable;

/**
 * Created by denis on 5/6/16.
 */
public class CloudCreateWalletUseCase extends UseCase<Wallet> {
    private WalletRepository repository;
    public CloudCreateWalletUseCase(ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread,
                                    WalletRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Wallet... arg) {
        return repository.addWallet(arg[0]);
    }
}
