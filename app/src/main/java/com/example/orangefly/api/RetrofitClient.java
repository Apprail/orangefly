package com.example.orangefly.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://172.22.246.105:9595/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public RetrofitApi getRetrofitApi(){
        return retrofit.create(RetrofitApi.class);
    }
}
