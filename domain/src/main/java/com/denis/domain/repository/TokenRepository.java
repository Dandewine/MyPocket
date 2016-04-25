package com.denis.domain.repository;

import rx.Observable;

/**
 * Created by denis on 4/24/16.
 */
public interface TokenRepository {
    Observable<String> get();
    Observable<String> put(String token);
}
