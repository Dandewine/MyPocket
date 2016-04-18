package com.denis.domain;

/**
 * Created by denis on 4/11/16.
 */
public interface RestClient {
    <T> T create(Class<T> clazz);
}
