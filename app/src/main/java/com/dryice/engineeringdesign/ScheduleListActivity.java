package com.dryice.engineeringdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

public class ScheduleListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ScheduleRecyclerAdapter scheduleRecyclerAdapter;
    private List<Schedule> scheduleList;
    private ScheduleDB scheduleDB = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.schedule_recyclerView);

        mContext = getApplicationContext();
        /* initiate adapter */
        scheduleRecyclerAdapter = new ScheduleRecyclerAdapter(scheduleList);
        // DB 생성
        scheduleDB = ScheduleDB.getInstance(this);

        // main thread에서 DB 접근 불가 => data 읽고 쓸 때 thread 사용하기
        class InsertRunnable implements Runnable {

            @Override
            public void run() {
                try {
                    scheduleList = ScheduleDB.getInstance(mContext).scheduleDao().getAll();
                    scheduleRecyclerAdapter = new ScheduleRecyclerAdapter(scheduleList);
                    scheduleRecyclerAdapter.notifyDataSetChanged();

                    mRecyclerView.setAdapter(scheduleRecyclerAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                }
                catch (Exception e) {

                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScheduleDB.destroyInstance();
        scheduleDB = null;
    }
}