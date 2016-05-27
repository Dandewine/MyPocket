package com.denis.mypocket.custom_views;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.denis.mypocket.R;

/**
 * Created by denis on 5/25/16.
 */

public class MorphingFloatingActionButton extends FloatingActionButton {

    private static final int COLOR_ANIM_DURATION = 500;

    private AnimatedVectorDrawable fromAnimatable;
    private AnimatedVectorDrawable toAnimatable;
    private int fromBackground, toBackground;
    private ValueAnimator colorFromAnimation, colorToAnimation;

    private boolean isSecondShowing = false;

    public MorphingFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MorphingFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {

        TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MorphingFloatingActionButton, 0, 0);
        try {
            fromBackground = ta.getColor(R.styleable.MorphingFloatingActionButton_backgroundColorFrom, Color.BLUE);
            toBackground = ta.getColor(R.styleable.MorphingFloatingActionButton_backgroundColorTo, Color.GREEN);
            fromAnimatable = (AnimatedVectorDrawable) ta.getDrawable(R.styleable.MorphingFloatingActionButton_animationFrom);
            toAnimatable = (AnimatedVectorDrawable) ta.getDrawable(R.styleable.MorphingFloatingActionButton_animationTo);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            ta.recycle();
        }

        setImageDrawable(fromAnimatable);
        setBackgroundTintList(ColorStateList.valueOf(fromBackground));

        colorToAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), toBackground, fromBackground).setDuration(COLOR_ANIM_DURATION);
        colorToAnimation.addUpdateListener(animation -> setBackgroundTintList(ColorStateList.valueOf((int) animation.getAnimatedValue())));

        colorFromAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromBackground, toBackground).setDuration(COLOR_ANIM_DURATION);
        colorFromAnimation.addUpdateListener(animation -> setBackgroundTintList(ColorStateList.valueOf((int) animation.getAnimatedValue())));
    }


    public void morphTo() {
        final AnimatedVectorDrawable drawable;

        if (isSecondShowing) {
            colorToAnimation.start();
            drawable = toAnimatable;
        } else {
            colorFromAnimation.start();
            drawable = fromAnimatable;
        }

        setImageDrawable(drawable);
        drawable.start();
        isSecondShowing = !isSecondShowing;
    }

}
