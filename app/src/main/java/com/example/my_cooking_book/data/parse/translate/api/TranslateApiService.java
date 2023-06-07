package com.example.my_cooking_book.data.parse.translate.api;

import com.example.my_cooking_book.data.parse.services.RetrofitTranslateService;

public class TranslateApiService {

    private static TranslateApi translateApi;

    private static TranslateApi create() {
        return RetrofitTranslateService.getInstance().create(TranslateApi.class);
    }

    public static TranslateApi getInstance() {
        if (translateApi == null) translateApi = create();
        return translateApi;
    }
}
