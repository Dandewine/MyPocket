package com.denis.domain.interactor.auth;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.TokenRepository;

import rx.Observable;

/**
 * Created by denis on 5/1/16.
 */
public class DeleteTokenUseCase extends UseCase {
    private TokenRepository repository;

    public DeleteTokenUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TokenRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Object[] arg) {
        return repository.delete();
    }
}
