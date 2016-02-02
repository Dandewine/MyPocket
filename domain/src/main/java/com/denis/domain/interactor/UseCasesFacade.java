package com.denis.domain.interactor;

import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

public class UseCasesFacade {
    private UseCase<Transaction> addTransactionUseCase;
    private UseCase<Wallet> getWalletsUseCase;
    private UseCase<IncomeCategory> incomeCategoryUseCase;
    private UseCase<ExpenseCategory> expenseCategoryUseCase;

    @Inject
    public UseCasesFacade(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                          @Named("getWallets") UseCase<Wallet> walletsUseCase,
                          @Named("incomeUC") UseCase<IncomeCategory> incomeCategoriesUseCase,
                          @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase) {
        this.addTransactionUseCase = addTransactionUseCase;
        this.getWalletsUseCase = walletsUseCase;
        this.incomeCategoryUseCase = incomeCategoriesUseCase;
        this.expenseCategoryUseCase = expenseCategoryUseCase;
    }

    public void getWallets(Subscriber subscriber){
        getWalletsUseCase.executeSync(subscriber);
    }

    public void addTransaction(Subscriber subscriber, Transaction transaction){
        addTransactionUseCase.executeSync(subscriber,transaction);
    }

    public void getIncomeCategories(Subscriber subscriber){
        incomeCategoryUseCase.executeSync(subscriber);
    }

    public void getExpenseCategories(Subscriber subscriber){
        expenseCategoryUseCase.executeSync(subscriber);
    }

    public void destroy(){
        addTransactionUseCase.unsubscribe();
        expenseCategoryUseCase.unsubscribe();
        incomeCategoryUseCase.unsubscribe();
        getWalletsUseCase.unsubscribe();
    }
}
