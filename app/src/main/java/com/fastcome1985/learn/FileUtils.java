package com.fastcome1985.learn;

import android.content.Context;
import android.os.Environment;

import java.util.HashMap;

/**
 * Created by luojianxiang on 17/5/27.
 */

public class FileUtils {


    /**
     * get storage path
     *
     * @return
     */
    public static String getPath(Context context) {
        if (isSDCardEnable()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            HashMap<String,String> hashMap=new HashMap<>();

            hashMap.put("11","111");
            return context.getFilesDir().getAbsolutePath();
        }
    }


    /**
     * whethe sd enable
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
