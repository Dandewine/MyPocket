package com.denis.mypocket.viewmodel.adding;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Debt;
import com.denis.mypocket.viewmodel.ViewModel;

/**
 * Created by denis on 2/20/16.
 */
public class AddDebtViewModel implements ViewModel {

    private UseCase<Debt> debtUseCase;

    public AddDebtViewModel(UseCase<Debt> debtUseCase) {
        this.debtUseCase = debtUseCase;
    }

    @Override
    public void destroy() {

    }
}
