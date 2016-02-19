package com.denis.mypocket.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Convertors {

    @BindingAdapter("bind:adapter")
    public static void bindAdapter(Spinner spinner, ArrayAdapter adapter) {
        if (spinner.getAdapter() == null)
            spinner.setAdapter(adapter);
    }

    @BindingAdapter("bind:adapter")
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter){
        if(recyclerView.getAdapter() == null){
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("bind:item_selected_listener")
    public static void setOnItemSelectedListener(Spinner spinner, AdapterView.OnItemSelectedListener listener){
        if(spinner.getOnItemSelectedListener() == null){
            spinner.setOnItemSelectedListener(listener);
        }
    }

}
