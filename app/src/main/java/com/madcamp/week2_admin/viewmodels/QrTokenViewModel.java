package com.madcamp.week2_admin.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.madcamp.week2_admin.models.QrTokenResponse;
import com.madcamp.week2_admin.repositories.QrTokenRepository;

/**
 * Created by jongwow on 2020-07-21.
 */
public class QrTokenViewModel extends AndroidViewModel {
    private QrTokenRepository qrTokenRepository;
    private LiveData<QrTokenResponse> qrTokenResponseLiveData;

    public QrTokenViewModel(@NonNull Application application){super(application);}

    public void init(){
        qrTokenRepository = new QrTokenRepository();
        qrTokenResponseLiveData = qrTokenRepository.getQrTokenResponseLiveData();
    }

    public void refreshQrToken(String token){
        qrTokenRepository.refreshQrToken(token);
    }

    public LiveData<QrTokenResponse> getQrTokenResponseLiveData(){
        return qrTokenResponseLiveData;
    }
}
