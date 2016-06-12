package com.denis.domain.interactor.categories;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.repository.ExpenseCategoriesRepository;

import java.util.List;

import rx.Observable;

/**
 * @author Denis_Zinkovskiy at 6/11/16.
 */

public class SaveExpenseCategoriesUseCase extends UseCase<List<ExpenseCategory>>{
    private ExpenseCategoriesRepository repository;

    public SaveExpenseCategoriesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ExpenseCategoriesRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(List<ExpenseCategory>... arg) {
        return repository.addExpenseCategory(arg[0]);
    }
}
