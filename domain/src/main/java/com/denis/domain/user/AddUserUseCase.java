package com.denis.domain.user;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;

import rx.Observable;

/**
 * Created by denis on 4/25/16.
 */
public class AddUserUseCase extends UseCase<User> {
    public AddUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(User... arg) {
        return null;
    }
}
