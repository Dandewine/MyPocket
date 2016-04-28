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
public class GetUserUseCase extends UseCase<User> {
    private UserRepository repository;

    public GetUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(User... arg) {
        return repository.login(arg[0].getId());
    }
}
