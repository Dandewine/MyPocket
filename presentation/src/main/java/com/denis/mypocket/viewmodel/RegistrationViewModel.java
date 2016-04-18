package com.denis.mypocket.viewmodel;


import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.User;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Inject;

/**
 * Created by denis on 4/2/16.
 */
@PerActivity
public class RegistrationViewModel implements ViewModel {

    private UseCase<User> loginUseCase;
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String>  email = new ObservableField<>();
    public ObservableField<String>  password = new ObservableField<>();

    @Inject
    public RegistrationViewModel(UseCase<User> loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public View.OnClickListener onClick = v -> execute();

    private void execute(){
        User user = getUserTest();
        loginUseCase.executeAsync(new RegistrationSubscriber(), user);
    }

    private User getUserTest() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setName("hello");

        return user;
    }

    @Override
    public void destroy() {
        loginUseCase.unSubscribe();
    }


    private class RegistrationSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(User user) {
            Log.d("myTag", "onNext: " + user.getName());
        }
    }
}
