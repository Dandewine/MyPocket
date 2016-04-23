package com.denis.mypocket.model;


/**
 * Created by denis on 12/20/15.
 */
public class UserModel {
    private String login;
    private String email;
    private String password;

    public UserModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserModel(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
