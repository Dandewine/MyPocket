package com.denis.data.entity.mapper;

import com.denis.data.ApplicationTestCase;
import com.denis.data.entity.UserEntity;
import com.denis.domain.models.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by denis on 4/10/16.
 */
public class UserDataMapperTest extends ApplicationTestCase {

    private final static String FAKE_USER_UUID_BATMAN = "c6b85d08-6e04-4259-873c-61b63ca21915";
    private final static String FAKE_USERNAME_BATMAN = "Batman";
    private final static String FAKE_USER_EMAIL_BATMAN = "batman@gmail.com";

    private final static String FAKE_USER_UUID_SUPERMAN = "fc0abdbf-7dc9-4da2-add7-cfab1a690a65";
    private final static String FAKE_USERNAME_SUPERMAN = "Superman";
    private final static String FAKE_USER_EMAIL_SUPERMAN = "superman@gmail.com";

    private UserDataMapper userDataMapper;

    @Before
    public void setUp(){
        userDataMapper = new UserDataMapper();
    }

    @Test
    public void testUserEntiityToUser(){
        UserEntity entity = buildFakeUserEntity_Batman();
        User user = userDataMapper.fromEntity(entity);

        assertThat(user,notNullValue());
        assertThat(user.getId(),is(FAKE_USER_UUID_BATMAN));
        assertThat(user.getEmail(), is(FAKE_USER_EMAIL_BATMAN));
        assertThat(user.getUsername(), is(FAKE_USERNAME_BATMAN));
    }

    @Test
    public void testListUserEntityToListUsers(){
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(buildFakeUserEntity_Batman());
        userEntities.add(buildFakeUserEntity_Superman());

        List<User> userList = userDataMapper.fromEntity(userEntities);

        assertThat(userList, notNullValue());
        assertThat(userList.size(),is(2));

        assertThat(userList.get(0).getId(), is(FAKE_USER_UUID_BATMAN));
        assertThat(userList.get(0).getUsername(), is(FAKE_USERNAME_BATMAN));
        assertThat(userList.get(0).getEmail(), is(FAKE_USER_EMAIL_BATMAN));

        assertThat(userList.get(1).getId(), is(FAKE_USER_UUID_SUPERMAN));
        assertThat(userList.get(1).getUsername(), is(FAKE_USERNAME_SUPERMAN));
        assertThat(userList.get(1).getEmail(), is(FAKE_USER_EMAIL_SUPERMAN));
    }

    @Test
    public void testUserToEntity(){
        User user = buildFakeUser_Batman();
        UserEntity entity = userDataMapper.toEntity(user);

        assertThat(entity,notNullValue());
        assertThat(entity.getId(),is(FAKE_USER_UUID_BATMAN));
        assertThat(entity.getEmail(), is(FAKE_USER_EMAIL_BATMAN));
        assertThat(entity.getName(), is(FAKE_USERNAME_BATMAN));
    }

    @Test
    public void testListUserToListUserEntity(){
        List<User> userList = new ArrayList<>();
        userList.add(buildFakeUser_Batman());
        userList.add(buildFakeUser_Superman());

        List<UserEntity> entityList = userDataMapper.toEntity(userList);

        assertThat(entityList, notNullValue());
        assertThat(entityList.size(),is(2));

        assertThat(entityList.get(0).getId(), is(FAKE_USER_UUID_BATMAN));
        assertThat(entityList.get(0).getName(), is(FAKE_USERNAME_BATMAN));
        assertThat(entityList.get(0).getEmail(), is(FAKE_USER_EMAIL_BATMAN));

        assertThat(entityList.get(1).getId(), is(FAKE_USER_UUID_SUPERMAN));
        assertThat(entityList.get(1).getName(), is(FAKE_USERNAME_SUPERMAN));
        assertThat(entityList.get(1).getEmail(), is(FAKE_USER_EMAIL_SUPERMAN));
    }

    private User buildFakeUser_Batman(){
        User user = new User(FAKE_USER_UUID_BATMAN);
        user.setUsername(FAKE_USERNAME_BATMAN);
        user.setEmail(FAKE_USER_EMAIL_BATMAN);
        return user;
    }

    private User buildFakeUser_Superman(){
        User user = new User(FAKE_USER_UUID_SUPERMAN);
        user.setUsername(FAKE_USERNAME_SUPERMAN);
        user.setEmail(FAKE_USER_EMAIL_SUPERMAN);
        return user;
    }

    private UserEntity buildFakeUserEntity_Batman(){
        UserEntity entity = new UserEntity(FAKE_USER_UUID_BATMAN);
        entity.setEmail(FAKE_USER_EMAIL_BATMAN);
        entity.setName(FAKE_USERNAME_BATMAN);
        return entity;
    }
    private UserEntity buildFakeUserEntity_Superman(){
        UserEntity entity = new UserEntity(FAKE_USER_UUID_SUPERMAN);
        entity.setEmail(FAKE_USER_EMAIL_SUPERMAN);
        entity.setName(FAKE_USERNAME_SUPERMAN);
        return entity;
    }


}
