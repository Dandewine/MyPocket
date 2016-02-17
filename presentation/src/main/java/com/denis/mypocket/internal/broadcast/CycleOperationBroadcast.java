package com.denis.mypocket.internal.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.cycle_operations.AddCircleOperationUseCase;
import com.denis.domain.models.CycleOperation;
import com.denis.mypocket.MyPocketApp;
import com.denis.mypocket.internal.di.components.ApplicationComponent;
import com.denis.mypocket.internal.di.components.DaggerAddCycleOPComponent;

import javax.inject.Inject;

/**
 * Will create a new ViewModel instance, even if we already has existing one
 */
public class CycleOperationBroadcast extends BroadcastReceiver {

    @Inject public AddCircleOperationUseCase useCase;
    private static int notificationID = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        initDI();
        CycleOperation operation = intent.getParcelableExtra("data");
        useCase.executeSync(new AlarmSetterSubscriber(context), operation);
        generateNotification(context);
    }

    private void initDI() {
        ApplicationComponent component = MyPocketApp.getPocketApp().getApplicationComponent();
        DaggerAddCycleOPComponent.builder()
                .applicationComponent(component)
                .build().inject(this);
    }

    private void generateNotification(Context context) {
       /* NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("This is test")
                .setContentText("Content test notification")
                .setTicker("This is MATCH!!!")
                .setSmallIcon(R.mipmap.ic_launcher);

        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setStyle(new NotificationCompat.BigTextStyle(builder));

        PendingIntent intent = PendingIntent.getBroadcast(context,0,new Intent(),0);
        builder.addAction(new NotificationCompat.Action(R.drawable.ic_menu_send,"Test",intent));

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(notificationID++, builder.build());*/
    }

    static class AlarmSetterSubscriber extends DefaultSubscriber<CycleOperation> {

        Context context;

        public AlarmSetterSubscriber(Context context) {
            this.context = context;
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(CycleOperation operation) {
            setAlarm(operation);
        }

        private void setAlarm(CycleOperation operation) {
          /*  AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent("action");
            intent.putExtra("data", operation);
            PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, operation.getTriggerTime()*1000, pi);
            else
                manager.setExact(AlarmManager.RTC_WAKEUP, operation.getTriggerTime()*1000, pi);*/

        }

    }
}
