package com.denis.domain.repository;

import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;

import java.util.List;

import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public interface UserRepository {
    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     *
     * @param UserId The getUser id used to retrieve getUser data.
     */
    Observable<User> getUser(final String UserId);
    Observable<LoginResponse> login(String body);
    /**
     * Add User into our local storage
     * @param user
     * @return
     */

    Observable<User> addUser(User user);

    /**
     * This method will drop database, because without user DB has no sense, and data can be corrupted
     * @return
     */
    Observable deleteUser();

    Observable update();

    Observable<List<User>> getAll();
}
