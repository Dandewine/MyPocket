package com.denis.domain.interactor;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.models.Debt;
import com.denis.domain.repository.DebtRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by denis on 2/20/16.
 */
public class AddDebtUseCase extends UseCase<Debt> {

    private DebtRepository debtRepository;

    @Inject
    public AddDebtUseCase(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          DebtRepository debtRepository) {
        super(threadExecutor, postExecutionThread);
        this.debtRepository = debtRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(Debt... arg) {
        return debtRepository.addDebt(arg[0]);
    }
}
