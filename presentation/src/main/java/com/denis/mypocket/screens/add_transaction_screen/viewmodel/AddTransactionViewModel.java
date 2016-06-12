package com.denis.mypocket.screens.add_transaction_screen.viewmodel;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.facades.AddTransactionUseCasesFacade;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.ExpenseCategoryModel;
import com.denis.mypocket.model.IncomeCategoryModel;
import com.denis.mypocket.model.TransactionModel;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.model.mapper.ExpenseCategoryModelMapper;
import com.denis.mypocket.model.mapper.IncomeCategoryModelMapper;
import com.denis.mypocket.model.mapper.TransactionModelDataMapper;
import com.denis.mypocket.model.mapper.WalletModelDataMapper;
import com.denis.mypocket.screens.add_transaction_screen.view.AddTransactionActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

@PerActivity
public class AddTransactionViewModel implements ViewModel {

    public static final String TRANSACTION_BUNDLE_KEY = "transaction.bundle.key";

    public String amount = "";
    private boolean isIncome;
    private long date;
    public ObservableField<String> dateFormat = new ObservableField<>();
    public ObservableInt isLoading = new ObservableInt(View.GONE);

    private ArrayAdapter categoriesAdapter;
    private AddTransactionUseCasesFacade transactionFacade;

    private IncomeCategoryModelMapper incomeMapper = new IncomeCategoryModelMapper();
    private ExpenseCategoryModelMapper expenseMapper = new ExpenseCategoryModelMapper();
    private WalletModelDataMapper walletModelDataMapper = new WalletModelDataMapper();
    private TransactionModelDataMapper transactionModelDataMapper = new TransactionModelDataMapper();

    private WalletModel walletModel;
    private Context context;

    @Inject
    public AddTransactionViewModel(AddTransactionUseCasesFacade transactionFacade, Context context,
                                   boolean isIncome) {

        this.transactionFacade = transactionFacade;
        this.isIncome = isIncome;
        this.context = context;
        initStartDate();

        categoriesAdapter = new ArrayAdapter<>(context, R.layout.item_category, R.id.categoryTitle_CSA);

        Log.d(PLTags.INSTANCE_TAG, "Add Transaction ViewModel, " + hashCode());
        transactionFacade.getWallets(new GetWalletsSubscriber());

        if (isIncome)
            transactionFacade.getIncomeCategories(new GetAllIncomeCategories());
        else
            transactionFacade.getExpenseCategories(new GetAllExpenseCategories());

    }

    private void initStartDate() {
        Date date = new Date();
        this.date = date.getTime();
        dateFormat.set(new SimpleDateFormat("dd/mm/yy", Locale.getDefault()).format(date));
    }

    public void afterTextChanged(Editable s) {
        if (!TextUtils.equals(s.toString(), amount))
            amount = s.toString();
        Log.d("myTag", amount);
    }

    @Override
    public void destroy() {
        Log.i(PLTags.INSTANCE_TAG, "AddTransactionViewModel has been destroyed");
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
            List<IncomeCategoryModel> models = incomeMapper.toModel(incomeCategories);
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
                List<ExpenseCategoryModel> modelList = expenseMapper.toModel(expenseCategories);
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
            Log.d(PLTags.TRANSACTIONS_TAG, "new transaction was added, id = " + transaction.getId());
            isLoading.set(View.GONE);

            TransactionModel model = transactionModelDataMapper.toModel(transaction);

            Intent intent = new Intent();
            intent.putExtra(TRANSACTION_BUNDLE_KEY,model);
            ((AddTransactionActivity) context).setResult(Activity.RESULT_OK,intent);
            ((AddTransactionActivity) context).finish();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private class GetWalletsSubscriber extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onNext(List<Wallet> wallets) {
            if (wallets != null && !wallets.isEmpty()) {
                List<WalletModel> walletModels = walletModelDataMapper.toModel(wallets);
                for (WalletModel model : walletModels) {
                    if (model.isActive()) {
                        walletModel = model;
                    }
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }
    }


    public View.OnClickListener addOnClick =
            v -> {
                if (!amount.isEmpty()) {
                    float amount = Float.parseFloat(this.amount.isEmpty() ? "0" : this.amount);
                    String type = isIncome ? Transaction.TransactionTypes.INCOME.name().toLowerCase() : Transaction.TransactionTypes.EXPENSE.name().toLowerCase();

                    Transaction transaction = new Transaction(walletModel.getId(), amount, type, "", date);
                    transactionFacade.addTransaction(new AddTransactionSubscriber(), transaction);
                    isLoading.set(View.VISIBLE);
                }else
                    Toast.makeText(context, R.string.empty_amount_error, Toast.LENGTH_SHORT).show();
            };


    public View.OnClickListener showDatePicker = v -> {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog pickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear + 1);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date = calendar.getTimeInMillis();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy", Locale.getDefault());
                dateFormat.set(simpleDateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pickerDialog.show();
    };


   /* public AdapterView.OnItemSelectedListener categoryOnClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.d("myTag", "itemClicked, position = " + position + ", id = " + id);
            categoryId = (int) id;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
*/

    public ArrayAdapter getCategoriesAdapter() {
        return categoriesAdapter;
    }
}
