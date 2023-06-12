package com.example.my_cooking_book.feature.search_recipe.search;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.my_cooking_book.data.parse.services.RetrofitIAMTokenService;
import com.example.my_cooking_book.data.parse.services.RetrofitTranslateService;
import com.example.my_cooking_book.data.parse.translate.IAMBody;
import com.example.my_cooking_book.data.parse.translate.IAMResponse;
import com.example.my_cooking_book.data.repository.Repository;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IAMWorker extends Worker {
    private static final String TAG = "IAMWorker";
    private static final long INTERVAL_ONE_HOUR = 60 * 60;

    public IAMWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        IAMBody iamBody = new IAMBody();
        iamBody.yandexPassportOauthToken = RetrofitIAMTokenService.OAUTH_TOKEN;
        Call<IAMResponse> iamResponseCall = Repository.getIAM(iamBody);
        iamResponseCall.enqueue(new Callback<IAMResponse>() {
            @Override
            public void onResponse(Call<IAMResponse> call, Response<IAMResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    IAMResponse iamResponse = response.body();
                    RetrofitTranslateService.AIM_TOKEN = iamResponse.iamToken;
                }
            }

            @Override
            public void onFailure(Call<IAMResponse> call, Throwable t) {

            }
        });

        return Result.success();
    }

    public static void schedulePeriodicWork() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(IAMWorker.class, INTERVAL_ONE_HOUR, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.REPLACE, workRequest);
    }
}
