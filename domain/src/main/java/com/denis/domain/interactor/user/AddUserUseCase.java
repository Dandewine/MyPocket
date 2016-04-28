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
public class AddUserUseCase extends UseCase<User> {

    private UserRepository userRepository;

    public AddUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(User... arg) {
        return userRepository.addUser(arg[0]);
    }
}
