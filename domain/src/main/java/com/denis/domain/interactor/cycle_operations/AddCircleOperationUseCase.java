package com.denis.domain.interactor.cycle_operations;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.transactions.AddTransactionUseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.CycleOperationRepository;

import javax.inject.Inject;

import rx.Observable;

public class AddCircleOperationUseCase extends UseCase<CycleOperation> {

    private CycleOperationRepository repository;
    private AddTransactionUseCase addTransactionUseCase;

    @Inject
    public AddCircleOperationUseCase(ThreadExecutor threadExecutor,
                                     PostExecutionThread postExecutionThread,
                                     CycleOperationRepository repository,
                                     AddTransactionUseCase addTransactionUseCase) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
        this.addTransactionUseCase = addTransactionUseCase;
    }

    @Override
    protected Observable buildUseCaseObservable(CycleOperation... arg) {
        Transaction transaction = arg[0].getTransaction();
        addTransactionUseCase.executeSync(new CycleTransactionSubscriber(), transaction);
        return repository.addCircleOperation(arg[0]);
    }

    static class CycleTransactionSubscriber extends DefaultSubscriber<Transaction> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(Transaction transaction) {

        }
    }


}
