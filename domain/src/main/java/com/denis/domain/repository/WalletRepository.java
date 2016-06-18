package com.denis.domain.repository;


import com.denis.domain.models.Wallet;
import com.denis.domain.models.categories.Category;

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
     * @param userId The getWallets id used to retrieve getWallets data.
     */
    Observable<Wallet> getWallet(final String userId);

    Observable<Wallet> addWallet(Wallet wallet);

    Observable<List<Wallet>> addWallet(List<Wallet> wallets);

    Observable<Wallet> update(Wallet item);

}
