package com.denis.mypocket.model;

public class IncomeCategoryModel {
    private String id;
    private String name;
    private String path;

    public IncomeCategoryModel(String id) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
