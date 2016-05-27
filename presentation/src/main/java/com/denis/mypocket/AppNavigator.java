package com.denis.mypocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denis.mypocket.screens.login_screen.view.SigInActivity;

/**
 * Created by denis on 4/23/16.
 */
public class AppNavigator {

    public AppNavigator(){}

    public void navigateToSignInActivity(Context context,@Nullable Bundle bundle){
        if (context != null) {
            Intent intent = SigInActivity.getCallingIntent(context, bundle);
            context.startActivity(intent);
        }
    }
}
