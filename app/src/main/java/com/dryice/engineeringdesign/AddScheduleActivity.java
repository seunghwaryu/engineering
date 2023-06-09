package com.dryice.engineeringdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddScheduleActivity extends AppCompatActivity {

    private ScheduleDB scheduleDB = null;
    private Context mContext;
    private EditText mEditTextName;
    private EditText mEditTextDeadline;
    private RadioGroup mradioGroup;
    private Button mAddButton;
    private String improtance = "not inputted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_schedule);

        mAddButton = (Button) findViewById(R.id.button_add);
        mEditTextName = (EditText) findViewById(R.id.edit_name);
        mEditTextDeadline = (EditText) findViewById(R.id.edit_deadline);
        mradioGroup = (RadioGroup) findViewById(R.id.radio_group);
        scheduleDB = ScheduleDB.getInstance(this);
        mContext = getApplicationContext();

        mradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.raido_a:
                        improtance = "A";
                        break;
                    case R.id.raido_b:
                        improtance = "B";
                        break;
                    case R.id.raido_c:
                        improtance = "C";
                        break;
                }
                Log.v("test","radio: "+improtance);
            }
        });

        class InsertRunnable implements Runnable {

            @Override
            public void run() {
                Log.v("test","run: "+improtance);
                Schedule schedule = new Schedule();
                schedule.name = mEditTextName.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    date = dateFormat.parse(mEditTextDeadline.getText().toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                schedule.deadline = date;
                schedule.importance = improtance;

                ScheduleDB.getInstance(mContext).scheduleDao().insertAll(schedule);
            }
        }

        mAddButton.setOnClickListener(v -> {

            InsertRunnable insertRunnable = new InsertRunnable();
            Thread addThread = new Thread(insertRunnable);
            addThread.start();

            Intent intent = new Intent(getApplicationContext(), ScheduleListActivity.class);
            startActivity(intent);
            finish();

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScheduleDB.destroyInstance();
    }

    //팝업 밖 터치시 팝업 종료 방지
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if( event.getAction() == MotionEvent.ACTION_OUTSIDE ) {
            return false;
        }
        return true;
    }
}