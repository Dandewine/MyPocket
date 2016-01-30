package com.denis.mypocket.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class AddTransactionViewModel implements ViewModel {
    public ObservableField<String> amount = new ObservableField<>();

    public UseCase<Transaction> addTransactionUseCase;
    private UseCase<Wallet> getWalletsUseCase;
    private UseCase<IncomeCategory> incomeCategoryUseCase;
    private UseCase<ExpenseCategory> expenseCategoryUseCase;

    private ArrayAdapter categoriesAdapter;
    private ArrayAdapter walletsAdapter;


    @Inject
    public AddTransactionViewModel(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                   @Named("getWallets") UseCase<Wallet> walletsUseCase,
                                   @Named("incomeUC") UseCase<IncomeCategory> incomeCategoriesUseCase,
                                   @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase,
                                   @Named("activity") Context context, boolean isIncome) {
        this.addTransactionUseCase = addTransactionUseCase;
        this.getWalletsUseCase = walletsUseCase;
        this.incomeCategoryUseCase = incomeCategoriesUseCase;
        this.expenseCategoryUseCase = expenseCategoryUseCase;

        categoriesAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);
        walletsAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);

        Log.d(PLTags.INSTANCE_TAG, "Add Transaction ViewModel, " + hashCode());

        walletsUseCase.executeSync(new GetAllWalletsSubscriber());
        if (isIncome)
            incomeCategoryUseCase.executeSync(new GetAllIncomeCategories());
        else
            expenseCategoryUseCase.executeSync(new GetAllExpenseCategories());
    }


    @Override
    public void destroy() {
        addTransactionUseCase.unsubscribe();
    }

    private class GetAllIncomeCategories extends DefaultSubscriber<List<IncomeCategory>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<IncomeCategory> incomeCategories) {
            for (int i = 0; i < incomeCategories.size(); i++) {
                categoriesAdapter.add(incomeCategories.get(i).getName());
            }
        }
    }

    private class GetAllExpenseCategories extends DefaultSubscriber<List<ExpenseCategory>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<ExpenseCategory> expenseCategories) {
            for (int i = 0; i < expenseCategories.size(); i++) {
                categoriesAdapter.add(expenseCategories.get(i).getName());
            }
        }
    }


    private class GetAllWalletsSubscriber extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Wallet> wallet) {
            for (int i = 0; i < wallet.size(); i++) {
                walletsAdapter.add(wallet.get(i).getName());
            }
        }
    }

    private static class AddTransactionSubscriber extends DefaultSubscriber<Transaction> {
        @Override
        public void onCompleted() {
            Log.d(PLTags.TRANSACTIONS_TAG, "Transaction was added");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(Transaction transaction) {
            Log.d(PLTags.TRANSACTIONS_TAG, "new transaction was added, id = " + transaction.getId());
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        amount.set(s.toString());
    }

    public View.OnClickListener addOnClick =
            v -> addTransactionUseCase.executeSync(new AddTransactionSubscriber(), new Transaction(0, 0, 345f, 1, 234L));

    public ArrayAdapter getCategoriesAdapter() {
        return categoriesAdapter;
    }

    public ArrayAdapter getWalletsAdapter() {
        return walletsAdapter;
    }
}
