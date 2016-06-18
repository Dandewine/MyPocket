package com.denis.domain.interactor.categories;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.repository.IncomeCategoriesRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetIncomeCategoriesUseCase extends UseCase<IncomeCategory> {

    private IncomeCategoriesRepository repository;

    @Inject
    public GetIncomeCategoriesUseCase(ThreadExecutor threadExecutor,
                                      PostExecutionThread postExecutionThread,
                                      IncomeCategoriesRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(IncomeCategory... arg) {
        return repository.getIncomeCategoryList();
    }
}
