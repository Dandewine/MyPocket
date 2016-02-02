package com.denis.domain.interactor;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.models.CircleOperation;

import rx.Observable;

public class AddCircleOperationUseCase extends UseCase<CircleOperation> {

    public AddCircleOperationUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(CircleOperation... arg) {
        return null;
    }
}
