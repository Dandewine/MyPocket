package com.denis.data.entity;



import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by denis on 1/5/16.
 */
public class UserEntity extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String email;
    private String password;


    public UserEntity() {
    }

    public UserEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
