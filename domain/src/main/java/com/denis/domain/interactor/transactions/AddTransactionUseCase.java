package com.denis.domain.interactor.transactions;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;

import javax.inject.Inject;

import rx.Observable;

public class AddTransactionUseCase extends UseCase<Transaction> {

    private TransactionRepository transactionRepository;

    @Inject
    public AddTransactionUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TransactionRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.transactionRepository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Transaction... arg) {
        return transactionRepository.addTransaction(arg[0]);
    }
}
