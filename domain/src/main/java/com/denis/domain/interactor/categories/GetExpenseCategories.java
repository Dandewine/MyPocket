package com.denis.domain.interactor.categories;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.ExpenseCategory;

import rx.Observable;

public class GetExpenseCategories extends UseCase<ExpenseCategory> {

    protected GetExpenseCategories(ThreadExecutor threadExecutor,
                                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(ExpenseCategory... arg) {
        return null;
    }
}
