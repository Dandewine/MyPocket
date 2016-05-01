package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.Token;

import rx.Observable;

/**
 * Created by denis on 4/24/16.
 */
public interface TokenDataStore {
    Observable<Token> get();
    Observable<Token> put(Token token);
    Observable<Boolean> delete();
}
