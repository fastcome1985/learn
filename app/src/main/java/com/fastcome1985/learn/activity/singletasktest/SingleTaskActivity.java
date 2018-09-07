package com.fastcome1985.learn.activity.singletasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fastcome1985.learn.R;

/**
 * Created by luojianxiang on 2017/8/3.
 */

public class SingleTaskActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletask);
        Log.i("ljx","----------SingleTaskActivity  onCreate---------taskId===="+getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("ljx","----------SingleTaskActivity  onNewIntent---------taskId===="+getTaskId());

    }
}
