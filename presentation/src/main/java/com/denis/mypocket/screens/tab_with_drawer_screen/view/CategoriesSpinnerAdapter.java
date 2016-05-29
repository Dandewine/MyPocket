package com.denis.mypocket.screens.tab_with_drawer_screen.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * @author denis at 5/28/16.
 */

public class CategoriesSpinnerAdapter extends ArrayAdapter<String> {

    public CategoriesSpinnerAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
