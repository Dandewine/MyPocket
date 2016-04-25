package com.denis.data.rest;

import android.util.Log;

import com.denis.data.DataConstants;
import com.denis.domain.RestClient;
import com.denis.domain.interactor.UseCase;

import java.io.IOException;
import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

@Singleton
public class RestClientRetrofit implements RestClient {

    private Retrofit retrofit;
    private UseCase<String> tokenUseCase;

    @Inject
    public RestClientRetrofit(UseCase<String> tokenUseCase) {
        this.tokenUseCase = tokenUseCase;
        init();
        Log.d(DataConstants.INSTANCE_TAG, "RestClientRetrofit: " + hashCode());
    }

    private void init() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(tokenUseCase))
                .addInterceptor(getLoggerInterceptor())
                .build();

        this.retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(RestAPI.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Override
    public <T> T create(final Class<T> clazz) {
        return retrofit.create(clazz);
    }

    static class TokenInterceptor implements Interceptor {
        private UseCase<String> tokenUseCase;
        private String token;

        TokenInterceptor(UseCase<String> tokenUseCase) {
            this.tokenUseCase = tokenUseCase;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            token = token == null ? getToken() : token;

            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Authorization",token)
                    .build();
            return chain.proceed(request);
        }

        private  String getToken(){
            final String[] token = {""};
            tokenUseCase.executeSync(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    token[0] = o;
                }
            });
            return token[0];
        }
    }

    private Interceptor getLoggerInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }




}
