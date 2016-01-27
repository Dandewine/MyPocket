package com.denis.mypocket.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Convertors {

    @BindingAdapter("bind:adapter")
    public static void bindAdapter(Spinner spinner, ArrayAdapter adapter) {
        if (spinner.getAdapter() == null)
            spinner.setAdapter(adapter);
    }

}
