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

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.domain.models.Transaction;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.ViewModel;

import javax.inject.Inject;

@PerActivity
public class AddCycleOperationViewModel implements ViewModel {
    private UseCase<CycleOperation> addUseCase;
    private Context context;
    public String name = "";

    @Inject
    public AddCycleOperationViewModel(UseCase<CycleOperation> addUseCase, Context context) {
        this.addUseCase = addUseCase;
        this.context = context;

        Log.i(PLTags.INSTANCE_TAG,"AddCycleOperationViewModel created, "+hashCode());
    }

    public View.OnClickListener addOnClick = v -> execute(
            new CycleOperation(new Transaction(45), name, CycleOperation.CircleTypes.WEEK.getValue()));

    @Override
    public void destroy() {
        addUseCase.unSubscribe();
    }

    public void afterTextChanged(Editable s) {
        if (!TextUtils.equals(s.toString(), name))
            name = s.toString();
    }

    public void execute(CycleOperation operation){
        addUseCase.executeSync(new AddCycleOperation(), operation);
    }

    class AddCycleOperation extends DefaultSubscriber<CycleOperation> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

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

    private void setAlarm(CycleOperation operation) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("action");
        intent.putExtra("key", "hello world!");
        intent.putExtra("data",operation);
        PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 3000, pi);
        else
            manager.setExact(AlarmManager.RTC_WAKEUP, 3000, pi);
    }
}
