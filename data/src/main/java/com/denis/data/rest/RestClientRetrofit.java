package com.denis.data.rest;

import android.util.Log;

import com.denis.data.DataConstants;
import com.denis.domain.RestClient;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestClientRetrofit implements RestClient {

    private Retrofit retrofit;

    @Inject
    public RestClientRetrofit() {
        init();
        Log.d(DataConstants.INSTANCE_TAG, "RestClientRetrofit: " + hashCode());
    }

    private void init() {
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(new TokenInterceptor())
                .addInterceptor(getLoggerInterceptor())
                .build();


        this.retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(RestAPI.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public <T> T create(final Class<T> clazz) {
        return retrofit.create(clazz);
    }

    static class TokenInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Authorization", "")
                    .build();
            return chain.proceed(request);
        }
    }

    private Interceptor getLoggerInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


}
