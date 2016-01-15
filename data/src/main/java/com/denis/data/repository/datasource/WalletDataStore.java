package com.denis.data.repository.datasource;

import com.denis.data.entity.WalletEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface WalletDataStore {

    /**
     *Get an {@link rx.Observable} which will emit a {@link WalletEntity} by its id.
     * @param walletId The id to retrieve wallet data.
     */
    Observable<WalletEntity> getWalletEntity(final int walletId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link WalletEntity}.
     */
    Observable<List<WalletEntity>> getListWalletEntities();

    Observable<WalletEntity> put(WalletEntity walletEntity);
    Observable<WalletEntity> put(Collection<WalletEntity> collection);
}
