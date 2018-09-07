package com.fastcome1985.learn;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fastcome1985.learn.util.FileEncryptUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private static final int BYTES = 1024;


    private Button partEncryptBtn;
    private Button partDecryptNewFileBtn;
    private Button partDecryptBtn;
    private Button fullEncryptBtn;
    private Button fullDecryptBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        partEncryptBtn = (Button) findViewById(R.id.encrypt_part);
        partDecryptNewFileBtn = (Button) findViewById(R.id.decrypt_part_newfile);
        partDecryptBtn = (Button) findViewById(R.id.decrypt_part);
        fullEncryptBtn = (Button) findViewById(R.id.encrypt_full);
        fullDecryptBtn = (Button) findViewById(R.id.decrypt_full);

        final String path = File.separator + "storage" + File.separator + "sdcard1" + File.separator + "a.mp4";
        final String newPath = File.separator + "storage" + File.separator + "sdcard1" + File.separator + "b.mp4";
        final String newPath2 = File.separator + "storage" + File.separator + "sdcard1" + File.separator + "c.mp4";
        final String newPath3 = File.separator + "storage" + File.separator + "sdcard1" + File.separator + "d.mp4";
        partEncryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FileEncryptUtil.encryptFilePart(path, FileEncryptUtil.getSecretkeyNew());
                        } catch (Exception e) {
                            Log.e("加密解密", "部分加密异常", e);
                        }
                    }
                }).start();
            }
        });

        partDecryptNewFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FileEncryptUtil.decryptFilePartNewFile(path, FileEncryptUtil.getSecretkeyNew(), newPath);
                        } catch (Exception e) {
                            Log.e("加密解密", "部分解密成新文件异常", e);
                        }
                    }
                }).start();
            }
        });

        partDecryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FileEncryptUtil.decryptFilePart(path, FileEncryptUtil.getSecretkeyNew());
                        } catch (Exception e) {
                            Log.e("加密解密", "部分解密异常", e);
                        }
                    }
                }).start();
            }
        });

        fullEncryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = new FileInputStream(path);
                            FileEncryptUtil.encryptFile(inputStream, FileEncryptUtil.getSecretkeyNew(), newPath2);
                        } catch (Exception e) {
                            Log.e("加密解密", "全文件加密异常", e);
                        }
                    }
                }).start();
            }
        });

        fullDecryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FileEncryptUtil.decryptFile(newPath2, FileEncryptUtil.getSecretkeyNew(), newPath3);
                        } catch (Exception e) {
                            Log.e("加密解密", "部分加密异常", e);
                        }
                    }
                }).start();
            }
        });


//        try {
//            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
//            Log.i("ljx", "appInfo.sourceDir====" + appInfo.sourceDir);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new AAA().start();


//        finish();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
////        testError();
////
////        Test.test();
////
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                File f = new File("/data/misc/qxgnss");
//                if (f.exists() && f.isDirectory()) {
//                    File[] files = f.listFiles();
//                    for (File file :
//                            files) {
//                        Log.i("ljx", file.getName());
//                    }
//                }


//                Intent intent = new Intent(MainActivity.this,FinishActivity.class);
//                startActivity(intent);
//                finish();

//                try {
//                    Log.i("tryTest", "000000");
//                    tryTest();
//                } catch (Exception e) {
//                    Log.i("tryTest", "5555555");
//                    e.printStackTrace();
//                }


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//                String path = "/storage/sdcard1/drivingrecorder";
//                File f = new File(path);
//                boolean flag = f.mkdirs();
//                if (flag) {
//                    Log.i("ljx", "success ==============");
//                } else {
//                    Log.i("ljx", "fail ==============");
//                }
//
//                String path = "/sdcard/source.mp4";
//                File f = new File(path);
//                String destPath = "/storage/sdcard1/55.mp4";
//                boolean flag = f.renameTo(new File(destPath));
//                if (flag) {
//                    Log.i("ljx", "1111111111");
//                } else {
//                    Log.i("ljx", "22222222222");
//                }
//
//            }
//        });
////
//////        String path = "/storage/sdcard1/drivingrecorder";
//////        File f = new File(path);
//////        boolean flag = f.mkdirs();
//////        if (flag) {
//////            Log.i("ljx", "success ==============");
//////        } else {
//////            Log.i("ljx", "fail ==============");
//////        }
////////        testSparseArray();
//////        Log.i("ljx", "====" + getFilesDir().getAbsolutePath());
//////
//////        File f3 = getDir("aaa"+File.separator+"bbb", Context.MODE_PRIVATE);
//////
//////        Log.i("ljx", "f3  ==============" + f3.getAbsolutePath());
//////        testT();
//////        Response response1=new Response();
//////        Response response2=response1;
//////        response1=null;
//////        Log.i("1111111",response2==null?"null":"not null");
////        TextTT();
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                Intent intent = new Intent(MainActivity.this, SingleTaskActivity.class);
////                startActivity(intent);
////                finish();
////            }
////        }, 2000);
////
//        Log.i("ljx","MainActivity=====taskId===="+ getTaskId());
////
////
////        Intent intent = new Intent(MainActivity.this, NewTaskService.class);
////        startService(intent);


    }


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("ljx", "7777777777777");
            switch (msg.what) {
                case 555:
                    Log.i("ljx", "是否主线程====" + (Looper.getMainLooper().getThread() == Thread.currentThread()));
                    break;
                default:
                    break;
            }
        }
    };


    private void TextTT() {
        ATT<Integer> att = new ATT<Integer>() {
            @Override
            public void at(Integer integer) {

            }
        };
        if (att instanceof ATT) {
            Log.i("11111111", "33333333333");
        }
        AT<Integer> at = new AT<Integer>() {
            @Override
            public void at(Integer integer) {

            }
        };
        if (at instanceof ATT) {
            Log.i("11111111", "444444444");
        }

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
        Log.i("ljx", "genType===" + genType.toString() + "  " + genType.getClass());


        ParameterizedType parametrizedType = (ParameterizedType) genType[0];
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
        Response response1 = JSONUtils.fromJSON(body, parametrizedType.getActualTypeArguments()[0]);

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


    private void tryTest() throws Exception {

        try {
            Log.i("tryTest", "11111111");
            String s = null;
            s.contains("cc");
        } catch (Exception e) {
            Log.i("tryTest", "222222222");
            throw e;
        } finally {
            Log.i("tryTest", "33333333");
            String s = "1234567890";
            s.substring(6);
            Log.i("tryTest", "4444444");
        }


    }


    class AAA extends Thread {


        @Override
        public void run() {
            super.run();

            Log.i("ljx", "111111111111111");
            Looper.prepare();

            Handler handler22 = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.i("ljx", "222222222222222");
                    switch (msg.what) {
                        case 555:
                            Log.i("ljx", "是否主线程====" + (Looper.getMainLooper().getThread() == Thread.currentThread()));
                            sendEmptyMessageDelayed(555, 3000);
                            break;
                        default:
                            break;
                    }
                }
            };

            handler22.sendEmptyMessageDelayed(555, 3000);
            Looper.loop();


        }
    }

}
