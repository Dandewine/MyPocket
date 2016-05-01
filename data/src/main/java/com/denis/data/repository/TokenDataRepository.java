package com.denis.data.repository;

import com.denis.data.entity.Token;
import com.denis.data.repository.datasource.interfaces.TokenDataStore;
import com.denis.domain.repository.TokenRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by denis on 4/24/16.
 */
@Singleton
public class TokenDataRepository implements TokenRepository {
    private TokenDataStore tokenDataStore;


    @Inject
    public TokenDataRepository(TokenDataStore tokenDataStore) {
        this.tokenDataStore = tokenDataStore;
    }

    @Override
    public Observable<String> get() {
        return tokenDataStore.get().map(Token::getToken);
    }

    @Override
    public Observable<String> put(String token) {
        return tokenDataStore.put(new Token(token)).map(Token::getToken);
    }

    @Override
    public Observable<Boolean> delete() {
        return tokenDataStore.delete();
    }
}
