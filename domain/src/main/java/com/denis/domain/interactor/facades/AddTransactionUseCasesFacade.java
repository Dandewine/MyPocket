package com.denis.domain.interactor.facades;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.categories.ExpenseCategory;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

public class AddTransactionUseCasesFacade {
    private UseCase<Transaction> addTransactionUseCase;
    private UseCase<Wallet> getWalletsUseCase;
    private UseCase<IncomeCategory> incomeCategoryUseCase;
    private UseCase<ExpenseCategory> expenseCategoryUseCase;

    @Inject
    public AddTransactionUseCasesFacade(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                        @Named("getWallets") UseCase<Wallet> walletsUseCase,
                                        @Named("incomeUC") UseCase<IncomeCategory> incomeCategoriesUseCase,
                                        @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase) {
        this.addTransactionUseCase = addTransactionUseCase;
        this.incomeCategoryUseCase = incomeCategoriesUseCase;
        this.expenseCategoryUseCase = expenseCategoryUseCase;
        this.getWalletsUseCase = walletsUseCase;
    }

    public void getWallets(Subscriber subscriber){
        getWalletsUseCase.executeSync(subscriber);
    }

    public void addTransaction(Subscriber subscriber, Transaction transaction){
        addTransactionUseCase.executeAsync(subscriber,transaction);
    }

    public void getIncomeCategories(Subscriber subscriber){
        incomeCategoryUseCase.executeSync(subscriber);
    }

    public void getExpenseCategories(Subscriber subscriber){
        expenseCategoryUseCase.executeSync(subscriber);
    }

    public void destroy(){
        addTransactionUseCase.unSubscribe();
        expenseCategoryUseCase.unSubscribe();
        incomeCategoryUseCase.unSubscribe();
        getWalletsUseCase.unSubscribe();
    }
}
