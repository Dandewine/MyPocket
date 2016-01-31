package com.denis.mypocket.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.ExpenseCategoryModel;
import com.denis.mypocket.model.IncomeCategoryModel;
import com.denis.mypocket.model.mapper.ExpenseCategoryModelMapper;
import com.denis.mypocket.model.mapper.IncomeCategoryModelMapper;
import com.denis.mypocket.utils.PLTags;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class AddTransactionViewModel implements ViewModel {
    public ObservableField<String> amount = new ObservableField<>();

    private ArrayAdapter categoriesAdapter;
    private ArrayAdapter walletsAdapter;
    private UseCasesFacade workerFacade;
    private IncomeCategoryModelMapper incomeMapper = new IncomeCategoryModelMapper();
    private ExpenseCategoryModelMapper expenseMapper = new ExpenseCategoryModelMapper();


    @Inject
    public AddTransactionViewModel(UseCasesFacade workerFacade,
                                   @Named("activity") Context context,
                                   boolean isIncome) {

        this.workerFacade = workerFacade;
        categoriesAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);
        walletsAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);

        Log.d(PLTags.INSTANCE_TAG, "Add Transaction ViewModel, " + hashCode());

        workerFacade.getWallets(new GetAllWalletsSubscriber());
        if (isIncome)
            workerFacade.getIncomeCategories(new GetAllIncomeCategories());
        else
            workerFacade.getExpenseCategories(new GetAllExpenseCategories());
    }


    @Override
    public void destroy() {
        Log.i(PLTags.INSTANCE_TAG,"AddTransactionViewModel has been destroyed");
        workerFacade.destroy();
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
            List<IncomeCategoryModel> models = incomeMapper.transform(incomeCategories);
            for (int i = 0; i < models.size(); i++) {
                categoriesAdapter.add(models.get(i).getName());
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
            List<ExpenseCategoryModel> modelList = expenseMapper.transform(expenseCategories);
            for (int i = 0; i < modelList.size(); i++) {
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
            v -> workerFacade.addTransaction(new AddTransactionSubscriber(), new Transaction( 0, 345f, 1, 234L));

    public ArrayAdapter getCategoriesAdapter() {
        return categoriesAdapter;
    }

    public ArrayAdapter getWalletsAdapter() {
        return walletsAdapter;
    }
}
