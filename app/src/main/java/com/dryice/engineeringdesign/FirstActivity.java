//앱을 켜자마자 보이는 첫 화면. 아직 그냥 기본 껍데기임! - 5/8 23시 이재우

package com.dryice.engineeringdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button addButton;
    private Button openButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //일정 추가 버튼
        addButton = (Button) findViewById(R.id.add_schedule_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddScheduleActivity.class);
                startActivity(intent);
            }
        });

        //일정 추가 버튼
        openButton = (Button) findViewById(R.id.open_schedule_button);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScheduleListActivity.class);
                startActivity(intent);
            }
        });
    }
}