package com.denis.data.local_store;

import android.text.TextUtils;

import com.denis.data.entity.Token;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;


/**
 * Created by denis on 4/24/16.
 */
@Singleton
public class TokenRealmStore implements RealmStore<Token> {

    private Realm realm;

    @Inject
    public TokenRealmStore(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<Token> get(int id) {
        //Realm objects can only be accessed on the thread they were created
        if (!TextUtils.equals(Thread.currentThread().getName(), "main")) {
            Realm realm = Realm.getDefaultInstance();
            Token first = realm.where(Token.class).findFirst();
            realm.close();
            if (first != null)
                return first.asObservable().map(realmObject -> (Token) realmObject);
            else
                return Observable.just(null);
        } else {
            Token token = realm.where(Token.class).findFirst();
            if (token != null)
                return token.asObservable().map(realmObject -> (Token) realmObject);
            else
                return Observable.just(null);
        }
    }

    @Override
    public Observable<Token> put(Token item) {
        realm.beginTransaction();
        realm.clear(Token.class);
        Token token = realm.copyToRealm(item);
        realm.commitTransaction();
        return Observable.just(token);
    }

    @Override
    public Observable<List<Token>> put(Collection<Token> collection) {
        throw new UnsupportedOperationException("Can't do this with token");
    }

    @Override
    public Observable<Token> update(Token item) {
        throw new UnsupportedOperationException("Can't do this with token");
    }

    @Override
    public Observable<List<Token>> getList() {
        throw new UnsupportedOperationException("Can't do this with token");
    }
}
