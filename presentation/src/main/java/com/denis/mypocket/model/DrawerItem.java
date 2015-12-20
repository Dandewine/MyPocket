package com.denis.mypocket.model;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

/**
 * Created by denis on 12/20/15.
 */
public class DrawerItem {
    public ObservableField<String> itemText = new ObservableField<>();
    public ObservableField<Drawable> icon = new ObservableField<>();
    public ObservableField<String> badge = new ObservableField<>();
    public ObservableField<Boolean> isBadgable = new ObservableField<>();

    public DrawerItem(String itemText, Drawable icon, String badge, boolean isBadgable) {
        this.itemText.set(itemText);
        this.icon.set(icon);
        this.badge.set(badge);
        this.isBadgable.set(isBadgable);
    }
}
