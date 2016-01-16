package com.denis.mypocket;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.denis.mypocket.view.OnRevealListener;

public class GUIUtils {
    public static void animateRvealShow(final Context context, final View view, final int startRadius, @ColorRes int color,
                                        int x, int y, OnRevealListener listener){
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());
        Animator animator = ViewAnimationUtils.createCircularReveal(view,x,y,startRadius,finalRadius);
        animator.setDuration(300);
        animator.setStartDelay(80);
        animator.setInterpolator(new FastOutLinearInInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setBackgroundColor(ContextCompat.getColor(context,color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
                if(listener != null) {
                    listener.onRevealShow();
                }
                animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
