package com.exercise.Facebook_exercise.apis;

import com.exercise.Facebook_exercise.models.QrTokenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jongwow on 2020-07-21.
 */
public interface QrTokenService {
    @GET("/api/v3/refresh")
    Call<QrTokenResponse> refreshQrToken(
            @Query("token") String token
    );
}
