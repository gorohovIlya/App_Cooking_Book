package com.example.my_cooking_book.data.parse.translate.response;

public class TranslateResponse {
    public class Translations{
        public String text;
        public String detectedLanguageCode;
    }

    public Translations [] translations;
}
