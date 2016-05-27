package com.denis.mypocket.custom_views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by denis on 4/22/16.
 */
public class FixedTextInputLayout extends TextInputLayout {
    public FixedTextInputLayout(Context context) {
        super(context);
    }

    public FixedTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setErrorEnabled(boolean enabled) {
        super.setErrorEnabled(enabled);
        if (enabled) {
            return;
        }
        if (getChildCount() > 1) {
            View view = getChildAt(1);
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }
    }
}
