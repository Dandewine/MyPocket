package com.denis.mypocket.viewmodel.adding;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCasesFacade;
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
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class AddTransactionViewModel implements ViewModel {
    public String amount = "";

    private int categoryId;
    private int walletId;

    private boolean isIncome;

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
        this.isIncome = isIncome;
        categoriesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
        walletsAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);

        Log.d(PLTags.INSTANCE_TAG, "Add Transaction ViewModel, " + hashCode());

        workerFacade.getWallets(new GetAllWalletsSubscriber());
        if (isIncome)
            workerFacade.getIncomeCategories(new GetAllIncomeCategories());
        else
            workerFacade.getExpenseCategories(new GetAllExpenseCategories());

    }


    public void afterTextChanged(Editable s){
        if (!TextUtils.equals(s.toString(), amount))
            amount = s.toString();
    }

    @Override
    public void destroy() {
        Log.i(PLTags.INSTANCE_TAG, "AddTransactionViewModel has been destroyed");
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

    private class AddTransactionSubscriber extends DefaultSubscriber<Transaction> {
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
            Log.d(PLTags.TRANSACTIONS_TAG, "new transaction was added, id = " + transaction.getId() + ", categoryId = " + categoryId);
        }
    }


    public View.OnClickListener addOnClick =
            v -> {
                Transaction transaction = new Transaction(walletId,
                        Float.parseFloat(amount),
                        isIncome ? 1 : 0,
                        categoryId,
                        System.currentTimeMillis() / 1000);
                workerFacade.addTransaction(new AddTransactionSubscriber(),
                        transaction);
            };

    public AdapterView.OnItemSelectedListener categoryOnClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.d("myTag", "itemClicked, position = " + position + ", id = " + id);
            categoryId = (int) id;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public AdapterView.OnItemSelectedListener walletOnCLickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            walletId = (int) id;
            Log.d("myTag", "wallet id = " + walletId);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public ArrayAdapter getCategoriesAdapter() {
        return categoriesAdapter;
    }

    public ArrayAdapter getWalletsAdapter() {
        return walletsAdapter;
    }
}
