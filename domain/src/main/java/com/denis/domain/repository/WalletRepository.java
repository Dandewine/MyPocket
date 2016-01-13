package com.denis.domain.repository;


import com.denis.domain.models.Wallet;

import java.util.List;

import rx.Observable;

public interface WalletRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Wallet}.
     */
    Observable<List<Wallet>> getWalletList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Wallet}.
     *
     * @param userId The getWallet id used to retrieve getWallet data.
     */
    Observable<Wallet> getWallet(final int userId);

    Observable<Wallet> addWallet(Wallet wallet);

    Observable<List<Wallet>> addWallet(List<Wallet> wallets);



}
