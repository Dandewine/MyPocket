package com.denis.domain.interactor.cycle_operations;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.domain.repository.CycleOperationRepository;

import javax.inject.Inject;

import rx.Observable;

public class AddCircleOperationUseCase extends UseCase<CycleOperation> {

    private CycleOperationRepository repository;

    @Inject
    public AddCircleOperationUseCase(ThreadExecutor threadExecutor,
                                     PostExecutionThread postExecutionThread,
                                     CycleOperationRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(CycleOperation... arg) {
        return repository.addCircleOperation(arg[0]);
    }
}
