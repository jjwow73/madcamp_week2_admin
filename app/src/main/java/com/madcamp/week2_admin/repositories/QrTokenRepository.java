package com.madcamp.week2_admin.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.madcamp.week2_admin.apis.QrTokenService;
import com.madcamp.week2_admin.models.QrTokenResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jongwow on 2020-07-21.
 */
public class QrTokenRepository {
    private static final String BASE_URL = "http://192.249.19.243:8780";
    private QrTokenService qrTokenService;
    private MutableLiveData<QrTokenResponse> qrTokenResponseLiveData;

    public QrTokenRepository() {
        qrTokenResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        qrTokenService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QrTokenService.class);

    }

    public void refreshQrToken(String token) {
        qrTokenService.refreshQrToken(token)
                .enqueue(new Callback<QrTokenResponse>() {
                    @Override
                    public void onResponse(Call<QrTokenResponse> call, Response<QrTokenResponse> response) {
                        if (response.body() != null) {
                            qrTokenResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<QrTokenResponse> call, Throwable t) {
                        qrTokenResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<QrTokenResponse> getQrTokenResponseLiveData() {
        return qrTokenResponseLiveData;
    }
}
