package com.example.mj.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mj.R;
import com.example.mj.db.Database;
import com.example.mj.login.LoginActivity;

/**
 * Created by 우상훈 on 2017-11-03.
 */


public class SplashActivity extends Activity {
    private final String logout_Code = "f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Database.getInstance().open(this); // 데이터 베이스 오픈
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = (new Intent(SplashActivity.this,LoginActivity.class));
                intent.putExtra("Logout_Code", logout_Code);
                startActivity(intent);
                finish();
            }
        }, 3000); // 3초 후 이미지를 닫습니다

    }



}
