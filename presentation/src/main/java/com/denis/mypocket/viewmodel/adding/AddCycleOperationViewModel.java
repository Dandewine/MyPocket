package com.denis.mypocket.viewmodel.adding;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.DateTimeUtils;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.TransactionModel;
import com.denis.mypocket.model.mapper.TransactionModelDataMapper;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity //// TODO: 2/8/16 implement this with the real data
public class AddCycleOperationViewModel implements ViewModel {
    private UseCase<CycleOperation> addUseCase;
    private UseCase<Wallet> getWalletUseCase;
    private UseCase<Transaction> transactionUseCase;
    private Context context;

    public String name = "";
    public String interval = "";

    private List<TransactionModel> transactionModels;
    private TransactionModelDataMapper dataMapper;

    private Transaction transaction;

    public ArrayAdapter transactionAdapter;

    @Inject
    public AddCycleOperationViewModel(UseCase<CycleOperation> addUseCase,
                                      @Named("getTransactions") UseCase<Transaction> transactionUseCase,
                                      @Named("getWallets") UseCase<Wallet> useCase,
                                      Context context, TransactionModelDataMapper dataMapper) {
        this.addUseCase = addUseCase;
        this.getWalletUseCase = useCase;
        this.context = context;
        this.dataMapper = dataMapper;
        this.transactionUseCase = transactionUseCase;
        transactionAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);
        this.transactionUseCase.executeSync(new GetTransactionSubscriber());


        buildCycleOperation();
        Log.i(PLTags.INSTANCE_TAG, "AddCycleOperationViewModel created, " + hashCode());
    }

    public View.OnClickListener addOnClick = v ->
            execute(buildCycleOperation());

    public RadioGroup.OnCheckedChangeListener changeListener = (group, checkedId) -> {
        switch (checkedId) {
            case R.id.rbDay:
                interval = CycleOperation.CircleTypes.DAY.getValue();
                break;
            case R.id.rbWeek:
                interval = CycleOperation.CircleTypes.WEEK.getValue();
                break;
            case R.id.rbMonth:
                interval = CycleOperation.CircleTypes.MONTH.getValue();
                break;
            case R.id.rbYear:
                interval = CycleOperation.CircleTypes.YEAR.getValue();
                break;
            case R.id.rbCustom:
                interval = CycleOperation.CircleTypes.CUSTOM.getValue();
                break;
        }
    };

    @Override
    public void destroy() {
        addUseCase.unSubscribe();
    }

    public void afterTextChanged(Editable s) {
        if (!TextUtils.equals(s.toString(), name))
            name = s.toString();
    }

    public void execute(CycleOperation operation) {
        addUseCase.executeSync(new AddCycleOperation(), operation);
    }

    private void setAlarm(CycleOperation operation) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("action");
        intent.putExtra("data", operation);
        PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            manager.set(AlarmManager.RTC_WAKEUP, operation.getTriggerTime(), pi);
        else
            manager.set(AlarmManager.RTC_WAKEUP, operation.getTriggerTime(), pi);
        Log.d(PLTags.CYCLE_OPERATIONS_TAG, "alarm was set on "+ DateTimeUtils.getFormattedShiftListItemDateTime(operation.getTriggerTime()));
    }

    public CycleOperation buildCycleOperation() {
        return new CycleOperation(transaction, name, interval);
    }

    //region subscribers

    class AddCycleOperation extends DefaultSubscriber<CycleOperation> {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(CycleOperation operation) {
            Log.d(PLTags.CYCLE_OPERATIONS_TAG, "cycle operation was added \n " + operation.toString());
            setAlarm(operation);
        }
    }

    class GetTransactionSubscriber extends DefaultSubscriber<List<Transaction>> {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Transaction> transactionList) {
            if (transactionList != null && !transactionList.isEmpty()) {
                transactionModels = dataMapper.transform(transactionList);
                for (int i = 0; i < transactionModels.size(); i++) {
                    transactionAdapter.add(transactionModels.get(i).getAmount());
                }
                //Note that I get always first element of the list
                transaction = transactionList.get(0);
            }
        }
    }

    //endregion
}
