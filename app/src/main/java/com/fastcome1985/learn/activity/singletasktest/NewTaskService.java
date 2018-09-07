package com.fastcome1985.learn.activity.singletasktest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.fastcome1985.learn.MainActivity;

/**
 * Created by luojianxiang on 2017/8/3.
 */

public class NewTaskService extends Service {


    int count = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    public void onCreate() {
        super.onCreate();
        handler.postDelayed(runnable, 3000);
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count++;
            if (count % 2 == 0) {
                Intent intent = new Intent(NewTaskService.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } else {
                Intent intent = new Intent(NewTaskService.this, StartSingleTaskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            handler.postDelayed(runnable, 5000);

        }
    };
}
