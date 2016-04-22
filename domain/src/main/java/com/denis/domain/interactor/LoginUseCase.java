package com.denis.domain.interactor;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.models.User;

import rx.Observable;

/**
 * Created by denis on 4/22/16.
 */
public class LoginUseCase extends UseCase<User> {
    public LoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(User... arg) {
        return null;
    }
}
