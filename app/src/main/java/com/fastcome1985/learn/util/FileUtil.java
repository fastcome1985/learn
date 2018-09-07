package com.fastcome1985.learn.util;

import java.io.File;

/**
 * Created by luojianxiang on 2018/8/31.
 */

public class FileUtil {


    /**
     * 获取文件大小
     *
     * @param file
     * @param unit
     * @return
     */
    public static double getSize(File file, SizeUnit unit) {
        long length = file.length();
        return (double) length / (double) unit.inBytes();
    }


}
