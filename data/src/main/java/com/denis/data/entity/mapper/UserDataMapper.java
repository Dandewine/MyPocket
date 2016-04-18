package com.denis.data.entity.mapper;

import com.denis.data.entity.UserEntity;
import com.denis.domain.models.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by denis on 4/10/16.
 */
public class UserDataMapper implements EntityMapper<UserEntity, User> {

    @Inject
    public UserDataMapper() {
    }

    @Override
    public UserEntity toEntity(User user) {
        UserEntity entity = null;
        if (user != null) {
            entity = new UserEntity(user.getId());
            entity.setEmail(user.getEmail());
            entity.setName(user.getName());
            entity.setPassword(user.getPassword());
        }
        return entity;
    }

    @Override
    public List<UserEntity> toEntity(List<User> users) {
        List<UserEntity> entityList = null;
        if (users != null) {
            entityList = new ArrayList<>();
            for (User user : users) {
                UserEntity entity = toEntity(user);
                entityList.add(entity);
            }
        }
        return entityList;
    }

    @Override
    public User fromEntity(UserEntity model) {
        User user = null;
        if (model != null) {
            user = new User(model.getId());
            user.setEmail(model.getEmail());
            user.setName(model.getName());
            user.setPassword(model.getPassword());
        }
        return user;
    }

    @Override
    public List<User> fromEntity(List<UserEntity> models) {
        List<User> userList = null;
        if (models != null) {
            userList = new ArrayList<>();
            for (UserEntity entity : models) {
                User user = fromEntity(entity);
                userList.add(user);
            }
        }
        return userList;
    }
}
