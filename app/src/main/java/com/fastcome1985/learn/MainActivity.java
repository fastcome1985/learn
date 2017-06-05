package com.fastcome1985.learn;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.fastcome1985.lib.Test;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static android.R.attr.type;
import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        testError();

        Test.test();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String path = "/storage/sdcard1/drivingrecorder";
                File f = new File(path);
                boolean flag = f.mkdirs();
                if (flag) {
                    Log.i("ljx", "success ==============");
                } else {
                    Log.i("ljx", "fail ==============");
                }
            }
        });

//        String path = "/storage/sdcard1/drivingrecorder";
//        File f = new File(path);
//        boolean flag = f.mkdirs();
//        if (flag) {
//            Log.i("ljx", "success ==============");
//        } else {
//            Log.i("ljx", "fail ==============");
//        }
////        testSparseArray();
//        Log.i("ljx", "====" + getFilesDir().getAbsolutePath());
//
//        File f3 = getDir("aaa"+File.separator+"bbb", Context.MODE_PRIVATE);
//
//        Log.i("ljx", "f3  ==============" + f3.getAbsolutePath());
        testT();

    }


    private void testT() {
        Response response = new Response();
        response.setId(11);
        String body = JSONUtils.toJSON(response);
        Log.i("ljx", "body===" + body);
        CallBack<Response> callBack = new CallBack<Response>() {
            @Override
            public void onSuccess(Response value) {
                Log.i("ljx", "value===" + value.getId());
            }
        };
        Type[] genType = callBack.getClass().getGenericInterfaces();
        Log.i("ljx", "genType===" + genType.toString()+ "  "+genType.getClass());


        ParameterizedType parametrizedType =(ParameterizedType) genType[0];
//        while (parametrizedType == null) {
//            if ((genType instanceof ParameterizedType)) {
//                Log.i("ljx", "genType===instanceof ParameterizedType");
//                parametrizedType = (ParameterizedType) genType;
//            }else if (genType instanceof GenericArrayType) {
//                //返回 Type 对象，表示声明此类型的类或接口。
//                Log.i("ljx", "genType===instanceof GenericArrayType");
//            } else {
//                Log.i("ljx", "genType===else");
//                genType = ((Class<?>) genType).getGenericSuperclass();
//            }
//        }


//        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Response response1 = JSONUtils.fromJSON(body,parametrizedType.getActualTypeArguments()[0]);

        callBack.onSuccess(response1);


    }

    private void testSparseArray() {
        SparseArray<String> array = new SparseArray<>();
        array.put(1, "a");
        array.put(2, "b");
        array.put(3, "c");
        array.put(4, "d");
        array.put(5, "e");
        for (int i = 0; i < array.size(); i++) {
            int key = array.keyAt(i);
//            array.removeAt(i);
            array.remove(key);
            Log.i("1111111", "key ====" + key + "  removed");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void testError() {

        try {
            String s = null;
            s.equals("d");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SDCardException("888", e.getMessage(), e.getCause());
        }

    }
}
