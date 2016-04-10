package com.denis.domain;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;

import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public class LoginUseCase extends UseCase {
    public LoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object[] arg) {
        return null;
    }
}
