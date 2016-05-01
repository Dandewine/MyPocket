package com.denis.data.repository.datasource.local;

import com.denis.data.entity.Token;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.TokenDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by denis on 4/24/16.
 */
@Singleton
public class TokenLocalDataStore implements TokenDataStore {

    private RealmStore<Token> tokenRealmStore;

    @Inject
    public TokenLocalDataStore(RealmStore<Token> tokenRealmStore) {
        this.tokenRealmStore = tokenRealmStore;
    }

    @Override
    public Observable<Token> get() {
        return tokenRealmStore.get(""); //db has only one token
    }

    @Override
    public Observable<Token> put(Token token) {
        return tokenRealmStore.put(token);
    }

    @Override
    public Observable<Boolean> delete() {
        return tokenRealmStore.delete(null);
    }
}
