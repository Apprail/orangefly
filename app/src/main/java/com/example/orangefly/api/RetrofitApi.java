package com.example.orangefly.api;

import com.example.orangefly.models.DefaultResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;

public interface RetrofitApi {

    @FormUrlEncoded
    @POST("/login/")
    Call<DefaultResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );
}
