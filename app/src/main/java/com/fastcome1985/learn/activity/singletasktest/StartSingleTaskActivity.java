package com.fastcome1985.learn.activity.singletasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fastcome1985.learn.R;

/**
 * Created by luojianxiang on 2017/8/3.
 */

public class StartSingleTaskActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartSingleTaskActivity.this, SingleTaskActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

        Log.i("ljx","StartSingleTaskActivity=====taskId===="+ getTaskId());


    }

    private Handler handler = new Handler(Looper.getMainLooper());
}
