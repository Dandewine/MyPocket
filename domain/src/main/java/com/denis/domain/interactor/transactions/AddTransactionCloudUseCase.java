package com.denis.domain.interactor.transactions;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;

import rx.Observable;

/**
 * @author denis at 5/28/16.
 */

public class AddTransactionCloudUseCase extends UseCase<Transaction> {
    private TransactionRepository transactionRepository;

    public AddTransactionCloudUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                      TransactionRepository transactionRepository) {
        super(threadExecutor, postExecutionThread);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Transaction... arg) {
        return transactionRepository.addTransaction(arg[0]);
    }
}
