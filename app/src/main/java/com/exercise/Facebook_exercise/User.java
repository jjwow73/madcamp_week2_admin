package com.exercise.Facebook_exercise;

import android.net.Uri;

/**
 * Created by jongwow on 2020-07-17.
 */

public class User {
    private final Uri picture;
    private final String name;
    private final String id;
    private final String email;

    public User(Uri picture, String name, String id, String email, String permissions) {
        this.picture = picture;
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public Uri getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}