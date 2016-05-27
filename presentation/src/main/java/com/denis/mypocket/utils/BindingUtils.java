package com.denis.mypocket.utils;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.denis.mypocket.R;
import com.denis.mypocket.custom_views.TextWatcherAdapter;

import java.util.Objects;

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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @BindingAdapter({"app:binding"})
    public static void bindEditText(final EditText view, String bindableString) {
        Pair<String, TextWatcherAdapter> pair = (Pair) view.getTag(R.id.bound_observable);
        if (pair == null || !Objects.equals(pair.first, bindableString)) {
            if (pair != null) {
                view.removeTextChangedListener(pair.second);
            }
            TextWatcherAdapter watcher = new TextWatcherAdapter() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    view.setText(s.toString());
                }
            };
            view.setTag(R.id.bound_observable, new Pair<>(bindableString, watcher));
            view.addTextChangedListener(watcher);
        }
        if (!view.getText().toString().equals(bindableString)) {
            view.setText(bindableString);
        }
    }

}