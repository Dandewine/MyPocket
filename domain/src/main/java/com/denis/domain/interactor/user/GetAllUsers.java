package com.denis.domain.interactor.user;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;

import rx.Observable;

/**
 * Created by denis on 4/25/16.
 */
public class GetAllUsers extends UseCase<User> {
    private UserRepository repository;

    public GetAllUsers(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(User... arg) {
        return repository.getAll();
    }
}
