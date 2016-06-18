package com.denis.domain.interactor.categories;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.repository.IncomeCategoriesRepository;

import java.util.List;

import rx.Observable;

/**
 * @author Denis_Zinkovskiy at 6/11/16.
 */

public class SaveIncomeCategoriesUseCase extends UseCase<List<IncomeCategory>> {
    private IncomeCategoriesRepository repository;

    public SaveIncomeCategoriesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IncomeCategoriesRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(List<IncomeCategory>... arg) {
        return repository.addIncomeCategory(arg[0]);
    }
}
