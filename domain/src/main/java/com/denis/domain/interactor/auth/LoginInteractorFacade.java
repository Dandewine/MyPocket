package com.denis.domain.interactor.auth;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.categories.ExpenseCategory;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author Denis_Zinkovskiy at 6/11/16.
 */


public class LoginInteractorFacade {
    private UseCase<String> loginUserCase;
    private UseCase<String> tokenSaveUseCase;
    private UseCase<User> userSaveUseCase;
    private UseCase<Wallet> getWalletUseCase;
    private UseCase<List<Wallet>> saveWalletUseCase;
    private UseCase<List<IncomeCategory>> saveIncomeCategoryUseCase;
    private UseCase<List<ExpenseCategory>> saveExpenseCategoryUseCase;

    @Inject
    public LoginInteractorFacade(UseCase<String> loginUserCase,
                                 UseCase<String> tokenSaveUseCase,
                                 UseCase<User> userSaveUseCase,
                                 UseCase<Wallet> getWalletUseCase,
                                 UseCase<List<Wallet>> saveWalletUseCase,
                                 UseCase<List<IncomeCategory>> saveIncomeCategoryUseCase,
                                 UseCase<List<ExpenseCategory>> expenseCategoryUseCase) {
        this.loginUserCase = loginUserCase;
        this.tokenSaveUseCase = tokenSaveUseCase;
        this.userSaveUseCase = userSaveUseCase;
        this.getWalletUseCase = getWalletUseCase;
        this.saveWalletUseCase = saveWalletUseCase;
        this.saveIncomeCategoryUseCase = saveIncomeCategoryUseCase;
        this.saveExpenseCategoryUseCase = expenseCategoryUseCase;
    }

    public void login(Subscriber subscriber, String body) {
        loginUserCase.executeAsync(subscriber, body);
    }

    public void saveToken(Subscriber subscriber, String token) {
        tokenSaveUseCase.executeSync(subscriber, token);
    }

    public void saveWallets(Subscriber subscriber, List<Wallet> wallets) {
        saveWalletUseCase.executeSync(subscriber, wallets);
    }

    public void getWallets(Subscriber subscriber) {
        getWalletUseCase.executeAsync(subscriber);
    }

    public void saveUser(Subscriber subscriber, User user) {
        userSaveUseCase.executeSync(subscriber, user);
    }

    public void saveIncomeCategories(Subscriber subscriber, List<IncomeCategory> incomeCategories) {
        saveIncomeCategoryUseCase.executeSync(subscriber, incomeCategories);
    }

    public void saveExpenseCategories(Subscriber subscriber, List<ExpenseCategory> expenseCategories) {
        saveExpenseCategoryUseCase.executeSync(subscriber, expenseCategories);
    }

    public void destroy(){
        loginUserCase.unSubscribe();
        tokenSaveUseCase.unSubscribe();
        userSaveUseCase.unSubscribe();
        getWalletUseCase.unSubscribe();
        saveWalletUseCase.unSubscribe();
        saveExpenseCategoryUseCase.unSubscribe();
        saveIncomeCategoryUseCase.unSubscribe();
    }

}
