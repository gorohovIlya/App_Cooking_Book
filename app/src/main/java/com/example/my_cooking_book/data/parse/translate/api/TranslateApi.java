package com.example.my_cooking_book.data.parse.translate.api;

import com.example.my_cooking_book.data.parse.translate.body.TranslateBody;
import com.example.my_cooking_book.data.parse.translate.response.TranslateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TranslateApi {

    @POST("/translate/v2/translate")
    @Headers("Content-Type: application/json")
    Call<TranslateResponse> getTranslate(@Header("Authorization") String iamToken,
                                         @Body TranslateBody translateBody);
}
