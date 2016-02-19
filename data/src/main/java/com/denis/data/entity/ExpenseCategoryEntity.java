package com.denis.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class ExpenseCategoryEntity extends RealmObject implements RealmObjectProxy {
    @PrimaryKey
    private int id;
    private String name;
    private String path;

    public ExpenseCategoryEntity(int id) {
        this.id = id;
    }

    public ExpenseCategoryEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExpenseCategoryEntity(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public ExpenseCategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
