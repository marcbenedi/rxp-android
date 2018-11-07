package com.realexpayments.hpp;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit.Ok3Client;

import okhttp3.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Helper class for server communication
 */
class ApiAdapter {

    public static final String RETROFIT_TAG = "HPPRetrofit";

    public static IHPPServerAPI getAdapter(String endpoint, OkHttpClient okHttpClient) {

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(RETROFIT_TAG))
                .setConverter(new GsonConverter(getGson()));

        if (okHttpClient != null){
            builder.setClient(new Ok3Client(okHttpClient));
        }

        RestAdapter restAdapter = builder.build();
        return restAdapter.create(IHPPServerAPI.class);
    }

    public static Gson getGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }
}
