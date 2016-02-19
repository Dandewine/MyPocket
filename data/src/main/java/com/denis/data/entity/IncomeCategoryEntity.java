package com.denis.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class IncomeCategoryEntity extends RealmObject implements RealmObjectProxy {
    @PrimaryKey
    private int id;
    private String name;
    private String path;

    public IncomeCategoryEntity(int id) {
        this.id = id;
    }

    public IncomeCategoryEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public IncomeCategoryEntity(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public IncomeCategoryEntity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
