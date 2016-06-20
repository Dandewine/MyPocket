package com.denis.domain.interactor.wallets;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;

import rx.Observable;

/**
 * @author Denis_Zinkovskiy at 6/20/16.
 */

public class UpdateWalletUseCase extends UseCase<Wallet> {
    private WalletRepository walletRepository;

    public UpdateWalletUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                               WalletRepository walletRepository) {
        super(threadExecutor, postExecutionThread);
        this.walletRepository = walletRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Wallet... arg) {
        return walletRepository.update(arg[0]);
    }
}
