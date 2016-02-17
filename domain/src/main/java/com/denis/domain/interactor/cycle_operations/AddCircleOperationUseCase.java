package com.denis.domain.interactor.cycle_operations;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.transactions.AddTransactionUseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.CycleOperationRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import rx.Observable;

public class AddCircleOperationUseCase extends UseCase<CycleOperation> {

    private CycleOperationRepository repository;
    private AddTransactionUseCase addTransactionUseCase;

    //unix time
    private static long YEAR = 31556926;
    private static long MONTH = 2629743;
    private static long WEEK = 604800;
    private static long DAY = 86400;

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
        setRepeat(arg[0]);
        Transaction transaction = arg[0].getTransaction();
        addTransactionUseCase.executeSync(new CycleTransactionSubscriber(), transaction);
        return repository.addCircleOperation(arg[0]);
    }

    private void setRepeat(CycleOperation operation) {
        String interval = operation.getInterval();

        switch (interval) {
            case "365": setTriggerTimePerYear(operation); break;
            case "30": setTriggerTimePerMonth(operation); break;
            case "7": setTriggerTimePerWeek(operation); break;
            case "24": setTriggerTimePerDay(operation); break;
            case "CUSTOM": break;
            default: break;

        }
    }

    private void setTriggerTimePerYear(CycleOperation operation) {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        boolean isLeapYear = calendar.isLeapYear(calendar.get(Calendar.YEAR));
        if (isLeapYear)
            operation.setTriggerTime(System.currentTimeMillis() + (YEAR + DAY) * 1000);
        else
            operation.setTriggerTime(System.currentTimeMillis() + YEAR * 1000);
    }

    private void setTriggerTimePerMonth(CycleOperation operation) {
        operation.setTriggerTime(System.currentTimeMillis() + MONTH * 1000);
    }

    private void setTriggerTimePerDay(CycleOperation operation) {
        operation.setTriggerTime(System.currentTimeMillis() + DAY * 1000);
    }

    private void setTriggerTimePerWeek(CycleOperation operation) {
        operation.setTriggerTime(System.currentTimeMillis() + WEEK * 1000);
    }

    private void setCustomTriggerTime(CycleOperation operation){
        String interval = operation.getInterval();

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
