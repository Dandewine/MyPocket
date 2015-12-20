package com.denis.mypocket.model;

import android.databinding.ObservableField;

/**
 * Created by denis on 12/20/15.
 */
public class User {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();

    public User(String name, String email) {
        this.name.set(name);
        this.email.set(email);
    }
}
