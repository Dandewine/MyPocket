package com.denis.domain.interactor.auth;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.repository.UserRepository;

import rx.Observable;

/**
 * Created by denis on 4/22/16.
 */
public class LoginUseCase extends UseCase<String> {
    private UserRepository userRepository;

    public LoginUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = repository;
    }

    @Override
    protected Observable<LoginResponse> buildUseCaseObservable(String... arg) {
        return userRepository.login(arg[0]);
    }
}
