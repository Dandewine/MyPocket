package com.denis.domain.interactor.auth;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.TokenRepository;

import rx.Observable;

/**
 * Created by denis on 4/24/16.
 */
public class TokenGet extends UseCase<String> {
    private TokenRepository repository;

    public TokenGet(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TokenRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(String... arg) {
        return repository.get();
    }
}
