package com.denis.domain.interactor;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;

import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public class RegistationUseCase extends UseCase {
    public RegistationUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object[] arg) {
        return null;
    }
}
