package com.denis.mypocket.viewmodel;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class BindingUtils {

    @BindingAdapter("bind:adapter")
    public static void bindAdapter(Spinner spinner, ArrayAdapter adapter) {
        if (spinner.getAdapter() == null)
            spinner.setAdapter(adapter);
    }

    @BindingAdapter("bind:adapter")
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("bind:item_selected_listener")
    public static void setOnItemSelectedListener(Spinner spinner, AdapterView.OnItemSelectedListener listener) {
        if (spinner.getOnItemSelectedListener() == null) {
            spinner.setOnItemSelectedListener(listener);
        }
    }

    @BindingAdapter("bind:typeface")
    static public void setTypeface(EditText editText, Typeface typeface) {
        if (editText != null && typeface != null)
            editText.setTypeface(typeface);
    }

}
