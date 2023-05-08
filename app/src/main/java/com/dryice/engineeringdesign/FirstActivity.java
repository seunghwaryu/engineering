//앱을 켜자마자 보이는 첫 화면. 아직 그냥 기본 껍데기임! - 5/8 23시 이재우

package com.dryice.engineeringdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
}