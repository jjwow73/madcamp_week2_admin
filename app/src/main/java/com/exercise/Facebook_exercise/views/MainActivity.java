package com.exercise.Facebook_exercise.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.exercise.Facebook_exercise.R;
import com.exercise.Facebook_exercise.models.QrTokenResponse;
import com.exercise.Facebook_exercise.viewmodels.QrTokenViewModel;
import com.facebook.login.LoginManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private QrTokenViewModel viewModel;
    private static final String TAG = "MainActivity";
    private ImageView qrImage;
    private Context mContext;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token = "";

        mContext = getApplicationContext();
        qrImage = findViewById(R.id.qr_image);

        viewModel = ViewModelProviders.of(this).get(QrTokenViewModel.class);
        viewModel.init();
        viewModel.getQrTokenResponseLiveData().observe(this, new Observer<QrTokenResponse>() {
            @Override
            public void onChanged(QrTokenResponse qrTokenResponse) {
                if (qrTokenResponse != null) {
                    qrImage.setImageBitmap(MainActivity.this.createQrImage(qrTokenResponse.getToken()));
                }
            }
        });

        findViewById(R.id.button_create_qr).setOnClickListener(this);
        findViewById(R.id.btn_facebook_logout).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_create_qr) {
            performRefreshQR();

        } else if (view.getId() == R.id.btn_facebook_logout) {
            Toast.makeText(this, "로그아웃 버튼클릭!", Toast.LENGTH_SHORT).show();
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(mContext, LoginActivity.class);
            finish();
            startActivity(intent);
        }
    }

    private void performRefreshQR() {
        Toast.makeText(this, "버튼클릭!", Toast.LENGTH_SHORT).show();
        viewModel.refreshQrToken(token);
    }

    private Bitmap createQrImage(String content) {

        MultiFormatWriter formatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = formatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
        } catch (Exception e) {
            Log.d(TAG, "createQrImage: Error");
            e.printStackTrace();
            return null;
        }
    }
}