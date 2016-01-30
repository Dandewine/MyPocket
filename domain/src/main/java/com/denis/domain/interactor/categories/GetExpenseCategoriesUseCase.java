package com.denis.domain.interactor.categories;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.repository.ExpenseCategoriesRepository;

import rx.Observable;

public class GetExpenseCategoriesUseCase extends UseCase<ExpenseCategory> {

    private ExpenseCategoriesRepository repository;

    public GetExpenseCategoriesUseCase(ThreadExecutor threadExecutor,
                                          PostExecutionThread postExecutionThread,
                                          ExpenseCategoriesRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(ExpenseCategory... arg) {
        return repository.getExpenseCategoryList();
    }
}
