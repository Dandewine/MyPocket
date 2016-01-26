package com.denis.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WasteCategoryEntity extends RealmObject {
    @PrimaryKey private int id;
    private String name;
    private String path;

    public WasteCategoryEntity(int id) {
        this.id = id;
    }

    public WasteCategoryEntity() {
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
