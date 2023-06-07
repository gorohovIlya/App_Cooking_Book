package com.example.my_cooking_book.data.parse.translate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAMTokenApi {

    @POST("/iam/v1/tokens")
    Call<IAMResponse> getIAM(@Body IAMBody iamBody);
}
