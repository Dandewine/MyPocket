package com.denis.mypocket.screens.add_transaction_screen.viewmodel;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.denis.domain.interactor.facades.AddTransactionUseCasesFacade;
import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.ExpenseCategoryModel;
import com.denis.mypocket.model.IncomeCategoryModel;
import com.denis.mypocket.model.mapper.ExpenseCategoryModelMapper;
import com.denis.mypocket.model.mapper.IncomeCategoryModelMapper;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class TransactionViewModel implements ViewModel {
    public String amount = "";

    private int categoryId;
    private boolean isIncome;

    private ArrayAdapter categoriesAdapter;
    private AddTransactionUseCasesFacade transactionFacade;
    private IncomeCategoryModelMapper incomeMapper = new IncomeCategoryModelMapper();
    private ExpenseCategoryModelMapper expenseMapper = new ExpenseCategoryModelMapper();

    public Typeface typeface;

    @Inject
    public TransactionViewModel(AddTransactionUseCasesFacade transactionFacade, Context context,
                                boolean isIncome) {

        this.transactionFacade = transactionFacade;
        this.isIncome = isIncome;
        categoriesAdapter = new ArrayAdapter<>(context, R.layout.item_category, R.id.categoryTitle_CSA);

        typeface = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");

        Log.d(PLTags.INSTANCE_TAG, "Add Transaction ViewModel, " + hashCode());

        if (isIncome)
            transactionFacade.getIncomeCategories(new GetAllIncomeCategories());
        else
            transactionFacade.getExpenseCategories(new GetAllExpenseCategories());

    }

    public void afterTextChanged(Editable s) {
        if (!TextUtils.equals(s.toString(), amount))
            amount = s.toString();
    }

    @Override
    public void destroy() {
        Log.i(PLTags.INSTANCE_TAG, "TransactionViewModel has been destroyed");
        transactionFacade.destroy();
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
            if (expenseCategories != null) {
                List<ExpenseCategoryModel> modelList = expenseMapper.transform(expenseCategories);
                for (int i = 0; i < modelList.size(); i++) {
                    categoriesAdapter.add(expenseCategories.get(i).getName());
                }
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

    class GetWalletsSubscriber extends DefaultSubscriber<List<Wallet>>{
        @Override
        public void onNext(List<Wallet> wallets) {
        /*    if(wallets != null && !wallets.isEmpty()) {
                WalletModelDataMapper walletModelDataMapper = dataMapper.getWalletModelDataMapper();
                walletModels = walletModelDataMapper.transform(wallets);
                for (WalletModel model : walletModels) {
                    walletsAdapter.add(model.getName());
                }
            }*/
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }
    }


    public View.OnClickListener addOnClick =
            v -> {
                float amount = Float.parseFloat(this.amount.isEmpty() ? "0" : this.amount);
                String type = isIncome ? Transaction.TransactionTypes.INCOME.name() : Transaction.TransactionTypes.EXPENSE.name();

                Transaction transaction = new Transaction(amount, type, categoryId, 0L);
                transactionFacade.addTransaction(new AddTransactionSubscriber(), transaction);
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

    public ArrayAdapter getCategoriesAdapter() {
        return categoriesAdapter;
    }
}
