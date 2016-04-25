package com.denis.domain.interactor.auth;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;

import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public class RegistrationUseCase extends UseCase<User> {
    private UserRepository userRepository;

    public RegistrationUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(User... users) {
        return userRepository.addUser(users[0]);
    }
}
