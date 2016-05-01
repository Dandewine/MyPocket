package com.denis.mypocket.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Inject;

/**
 * Created by denis on 4/29/16.
 */
@PerActivity
public class DrawerNavViewModel implements ViewModel, View.OnClickListener {
    private UseCase logoutUseCase;
    private UseCase deleteUser, deleteTokenUseCase;
    private Context context;

    @Inject
    public DrawerNavViewModel(UseCase logoutUseCase, UseCase deleteUser, UseCase deleteTokenUseCase, Context context) {
        this.logoutUseCase = logoutUseCase;
        this.deleteUser = deleteUser;
        this.deleteTokenUseCase = deleteTokenUseCase;
        this.context = context;
    }

    @Override
    public void destroy() {
        logoutUseCase.unSubscribe();
    }

    public void logout() {
        logoutUseCase.executeAsync(new LogoutSubscriber());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addExpenseTrans:
                Log.d("myTag", "works");
                //startAddTransactionAct(false);
                break;
            case R.id.addIncomeTrans:
                //startAddTransactionAct(true);
                break;
            case R.id.fabTrans:
                // animateFAB();
                break;
            case R.id.fabCyclic:
                //  Snackbar.make(fabCyclic, "Cyclic", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.fabSaves:
                // Snackbar.make(fabSaves, "Saves", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.fabStats:
                //  Snackbar.make(fabStats, "Stats", Snackbar.LENGTH_SHORT).show();
                break;

            default:
                throw new IllegalArgumentException("Can't recognize incoming ID");
        }
    }

    private class LogoutSubscriber extends DefaultSubscriber<Integer> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(Integer integer) {
            Log.d("myTag", "code = " + integer);
            deleteUser.executeSync(new DeleteUserSubscriber());
            deleteTokenUseCase.executeSync(new DeleteTokenSubscriber());
        }
    }

    private class DeleteUserSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            Log.d("myTag","" +aBoolean);
        }
    }

    private class DeleteTokenSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onNext(Boolean o) {
            Log.d("myTag","" +o);
        }
    }
}
