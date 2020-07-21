package com.exercise.Facebook_exercise.lib;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.exercise.Facebook_exercise.views.LoginActivity;
import com.facebook.login.LoginManager;

/**
 * Created by jongwow on 2020-07-21.
 */
public class BackPressHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;


    public BackPressHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        }
    }
    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 로그아웃이 됩니다.", Toast.LENGTH_SHORT); toast.show();
    }
}