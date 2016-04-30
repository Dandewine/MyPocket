package com.denis.domain.interactor.auth;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.UserRepository;

import rx.Observable;

/**
 * Created by denis on 4/29/16.
 */
public class LogoutUseCase extends UseCase {
    private UserRepository userRepository;

    public LogoutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Object... arg) {
        return userRepository.logout();
    }
}
