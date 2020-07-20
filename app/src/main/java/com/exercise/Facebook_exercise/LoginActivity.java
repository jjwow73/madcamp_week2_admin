package com.exercise.Facebook_exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by jongwow on 2020-07-17.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Context mContext;
//    AccessTokenTracker accessTokenTracker;
//    AccessToken accessToken;

    private LoginButton btn_facebook_login;
    private TextView textView;

    private LoginCallback mLoginCallback;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = getApplicationContext();

//        FacebookSdk.sdkInitialize(mContext);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new LoginCallback();

        btn_facebook_login = (LoginButton) findViewById(R.id.btn_facebook_login);
        btn_facebook_login.setReadPermissions(Arrays.asList("public_profile", "email"));
        btn_facebook_login.registerCallback(mCallbackManager, mLoginCallback);

        textView = findViewById(R.id.textView);

    }

    private void showProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                String name = null;
                try {
                    name = object.getString("name");

                } catch (Exception e) {
                    Log.d(TAG, "onCompleted: Error");
                    e.printStackTrace();
                }
                Toast.makeText(LoginActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCompleted: User Name:" + name);
//                RequestOptions requestOptions = new RequestOptions();
//                requestOptions.dontAnimate();
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name, email, id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requesetCode is RESULT_OK");
//        Toast.makeText(mContext, "Result: OK", Toast.LENGTH_SHORT).show();
        if (AccessToken.getCurrentAccessToken() != null) {
            Log.d(TAG, "onActivityResult:  UserId:" + AccessToken.getCurrentAccessToken().getUserId());
            Log.d(TAG, "run: 현재 로그인상태: O");
            Intent intent = new Intent(mContext, MainActivity.class);
            finish();
            startActivity(intent);
        } else {
            textView.setText("로그아웃상태");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}