package com.fastcome1985.learn.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fastcome1985.learn.MainActivity;
import com.fastcome1985.learn.R;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * Created by luojianxiang on 2017/8/1.
 */

public class FinishActivity extends Activity{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Log.i("FinishActivity","11111111111111");
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
