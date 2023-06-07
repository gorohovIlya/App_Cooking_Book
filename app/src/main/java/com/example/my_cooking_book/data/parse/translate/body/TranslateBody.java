package com.example.my_cooking_book.data.parse.translate.body;

import com.example.my_cooking_book.data.parse.services.RetrofitTranslateService;

public class TranslateBody {
    public String folderId = RetrofitTranslateService.FOLDER_ID;
    public String [] texts;
    public String targetLanguageCode;
}
