package com.denis.mypocket.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denis.mypocket.R;

public class SigInActivity extends BaseActivity {

    public static void toSignInActivity(Context context, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, SigInActivity.class);
        if (bundle != null)
            intent.putExtra(SigInActivity.class.getName(), bundle);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initDIComponent() {

    }
}
