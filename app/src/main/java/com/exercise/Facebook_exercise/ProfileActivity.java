package com.exercise.Facebook_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements  GetUserCallback.IGetUserResponse{
    private ImageView mImageView;
    private TextView mName;
    private TextView mId;
    private TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mImageView = findViewById(R.id.profile_photo);
        mName = findViewById(R.id.name);
        mId = findViewById(R.id.id);
        mEmail = findViewById(R.id.email);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserRequest.makeUserRequest(new GetUserCallback(ProfileActivity.this).getCallback());
    }

    @Override
    public void onCompleted(User user) {
        mImageView.setImageURI(user.getPicture());
        mName.setText(user.getName());
        mId.setText(user.getId());
        if (user.getEmail() == null) {
            mEmail.setText("Email이 없습니다.");
            mEmail.setTextColor(Color.RED);
        } else {
            mEmail.setText(user.getEmail());
            mEmail.setTextColor(Color.BLACK);
        }
    }
}