package com.denis.domain.repository;


import com.denis.domain.models.Wallet;

import java.util.List;

import rx.Observable;

public interface WalletRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Wallet}.
     */
    Observable<List<Wallet>> users();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Wallet}.
     *
     * @param userId The user id used to retrieve user data.
     */
    Observable<Wallet> user(final int userId);
}
