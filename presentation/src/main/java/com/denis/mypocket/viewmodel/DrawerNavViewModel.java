package com.denis.mypocket.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.model.mapper.ModelMapper;
import com.denis.mypocket.model.mapper.UserModelMapper;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import rx.Subscriber;

/**
 * Created by denis on 4/29/16.
 */
@PerActivity // TODO: 5/26/16 MUltiple instance of this class, after logout
public class DrawerNavViewModel implements ViewModel, View.OnClickListener {
    private UseCase logoutUseCase;
    private UseCase deleteUser;
    private UseCase deleteTokenUseCase;

    private Context context;
    private ModelMapper<User, UserModel> userModelMapper = new UserModelMapper();
    private UserModel userModel = null;

    private Subscriber<List<User>> userSubscriber = new DefaultSubscriber<List<User>>() {
        @Override
        public void onNext(List<User> users) {
            if (users != null && !users.isEmpty())
                userModel = userModelMapper.transform(users.get(0));
        }
    };

    @Inject
    public DrawerNavViewModel(UseCase logoutUseCase,
                              UseCase deleteUser,
                              UseCase deleteTokenUseCase,
                              UseCase<User> getUserUseCase,
                              Context context) {
        this.logoutUseCase = logoutUseCase;
        this.deleteUser = deleteUser;
        this.deleteTokenUseCase = deleteTokenUseCase;
        this.context = context;

        getUserUseCase.executeSync(userSubscriber);
    }

    @Override
    public void destroy() {
        userModelMapper = null;
        context = null;
        deleteTokenUseCase.unSubscribe();
        deleteUser.unSubscribe();
        logoutUseCase.unSubscribe();
    }

    public void logout() {
        logoutUseCase.executeAsync(new LogoutSubscriber());
    }

    public UserModel getUser() {
        return userModel;
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

    @RxLogSubscriber
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
        public void onNext(Integer code) {
            if (code == HttpsURLConnection.HTTP_OK)
                deleteTokenUseCase.executeSync(new DeleteTokenSubscriber());

        }
    }

    @RxLogSubscriber
    private class DeleteTokenSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onNext(Boolean isTokenDeleted) {
            if (isTokenDeleted)
                deleteUser.executeSync(new DeleteUserSubscriber());
        }
    }

    @RxLogSubscriber
    private class DeleteUserSubscriber extends DefaultSubscriber<Boolean> {
        @Override
        public void onNext(Boolean isTokenDeleted) {
            if (isTokenDeleted) {
                String packageName = context.getPackageName();
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }
    }
}
