package com.denis.mypocket.model.mapper;

import com.denis.domain.models.User;
import com.denis.mypocket.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 5/2/16.
 */
public class UserModelMapper implements ModelMapper<User,UserModel> {

    @Override
    public UserModel transform(User user) {
        UserModel model = null;
        if(user != null){
            model = new UserModel(user.getId());
            model.setEmail(user.getEmail());
            model.setPassword(user.getPassword());
            model.setUsername(user.getUsername());
        }
        return model;
    }

    @Override
    public List<UserModel> transform(List<User> users) {
        List<UserModel> models = null;
        if(users != null && !users.isEmpty()){
            models = new ArrayList<>();
            for (User user : users) {
                UserModel model = transform(user);
                models.add(model);
            }
        }
        return models;
    }
}
