package com.denis.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class IncomeCategoryEntity extends RealmObject {
    @PrimaryKey @SerializedName("id") private String id;
    @SerializedName("name") private String name;
    private int path;

    public IncomeCategoryEntity(String id) {
        this.id = id;
    }

    public IncomeCategoryEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public IncomeCategoryEntity(String id, String name, int path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public IncomeCategoryEntity() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

}
