package com.denis.domain.models;

public class ExpenseCategory {
    private String id;
    private String name;
    private int path;

    public ExpenseCategory(String id) {
        this.id = id;
    }

    public ExpenseCategory(String id, String name, int path) {
        this.id = id;
        this.name = name;
        this.path = path;
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

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
