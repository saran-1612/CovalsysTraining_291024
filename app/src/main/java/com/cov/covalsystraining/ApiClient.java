package com.cov.covalsystraining;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BASE_URL ="http://192.168.30.75:8086/";

    private static Retrofit retrofit;

    public static Retrofit getClient(Context mContext){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //setup cache

        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB

        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(2, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                .readTimeout(2, TimeUnit.MINUTES) // read timeout
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    return chain.proceed(request);
                }).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

            if (retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .build();
            }
            return retrofit;
    }

}

 /*if (retrofitSignalStrength == null) {
         HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
         logging.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient client = new OkHttpClient.Builder()
         .addInterceptor(logging)
         .connectTimeout(1000, TimeUnit.SECONDS)
         .readTimeout(1000,TimeUnit.SECONDS)
         .build();
         retrofitSignalStrength = new
         Retrofit.Builder().baseUrl(BASE_URL_STAT)
         .addConverterFactory(GsonConverterFactory.create())
         .client(client)
         .build();
         }
         return retrofitSignalStrength;*/