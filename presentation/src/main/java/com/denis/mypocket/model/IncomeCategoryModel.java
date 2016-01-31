package com.denis.mypocket.model;

public class IncomeCategoryModel {
    private int id;
    private String name;
    private String path;

    public IncomeCategoryModel(int id) {
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
