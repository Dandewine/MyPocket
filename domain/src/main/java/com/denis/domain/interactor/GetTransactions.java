package com.denis.domain.interactor;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetTransactions extends UseCase<Transaction> {

    private TransactionRepository repository;

    @Inject
    public GetTransactions(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread,
                           TransactionRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Transaction... arg) {
        return repository.getTransactionList();
    }
}
