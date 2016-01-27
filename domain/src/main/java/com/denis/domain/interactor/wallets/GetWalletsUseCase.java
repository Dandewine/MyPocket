package com.denis.domain.interactor.wallets;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetWalletsUseCase extends UseCase<Wallet> {

    private WalletRepository repository;

    @Inject
    public GetWalletsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, WalletRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Wallet... wallets) {
        return repository.getWalletList();
    }
}
