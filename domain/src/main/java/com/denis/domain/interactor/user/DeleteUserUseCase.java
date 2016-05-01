package com.denis.domain.interactor.user;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.UserRepository;

import rx.Observable;

/**
 * Created by denis on 4/30/16.
 */
public class DeleteUserUseCase extends UseCase {
    private UserRepository repository;

    public DeleteUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Object... arg) {
        return repository.deleteUser();
    }
}
