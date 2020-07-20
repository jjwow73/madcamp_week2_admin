package com.exercise.Facebook_exercise.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.exercise.Facebook_exercise.R;
import com.exercise.Facebook_exercise.views.LoginActivity;
import com.exercise.Facebook_exercise.views.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

/**
 * Created by jongwow on 2020-07-20.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private Context mContext;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = getApplicationContext();

        FacebookSdk.sdkInitialize(mContext);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    Log.d(TAG, "AccessTokenTracker: logout catched");
                } else {
                    Log.d(TAG, "AccessTokenTracker: logIN");
                }
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        accessTokenTracker.startTracking();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Log.d(TAG, "run: 현재 로그인상태: X");
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Log.d(TAG, "run: 현재 로그인상태: O");
                    Intent intent = new Intent(mContext, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        }, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
}
