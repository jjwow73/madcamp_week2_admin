package com.exercise.Facebook_exercise.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jongwow on 2020-07-21.
 */
public class QrTokenResponse {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("msg")
    @Expose
    private String message;

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
