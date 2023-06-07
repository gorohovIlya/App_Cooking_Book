package com.example.my_cooking_book.data.parse.translate;

import com.example.my_cooking_book.data.parse.services.RetrofitIAMTokenService;

public class IAMTokenApiService {
    private static IAMTokenApi iamTokenApi;

    private static IAMTokenApi create() {
        return RetrofitIAMTokenService.getInstance().create(IAMTokenApi.class);
    }

    public static IAMTokenApi getInstance() {
        if (iamTokenApi == null) iamTokenApi = create();
        return iamTokenApi;
    }
}
