package com.madcamp.week2_admin.models;

/**
 * Created by jongwow on 2020-07-21.
 */
public class QrToken {
    private String token;

    public QrToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
