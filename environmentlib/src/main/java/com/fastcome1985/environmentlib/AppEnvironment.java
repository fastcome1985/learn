package com.fastcome1985.environmentlib;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by luojianxiang on 2018/9/6.
 */

public class AppEnvironment {

    private static final int ENVITONMENT_DEBUG = 1;
    private static final int ENVITONMENT_PRE_RELEASE = 2;
    private static final int ENVITONMENT_RELEASE = 3;

    /**
     * 环境
     */
    private static Integer envitonment = null;

    /**
     * 初始化环境
     * 根据manififest打包环境自动判断环境
     *
     * @param context
     */
    public static void initEnvironment(Context context) {
        if (envitonment != null) {
            throw new RuntimeException("AppEnvironment can only init once！");
        }
        ApplicationInfo info = context.getApplicationInfo();
        if (info != null && (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            initDebugEnvironment();
        } else {
            initReleaseEnvironment();
        }
    }

    /**
     * 初始化debug环境
     */
    public static void initDebugEnvironment() {
        if (envitonment != null) {
            throw new RuntimeException("AppEnvironment can only init once！");
        }
        envitonment = ENVITONMENT_DEBUG;
    }

    /**
     * 初始化预发布环境
     */
    public static void initPreReleaseEnvironment() {
        if (envitonment != null) {
            throw new RuntimeException("AppEnvironment can only init once！");
        }
        envitonment = ENVITONMENT_PRE_RELEASE;
    }

    /**
     * 初始化正式环境
     */
    public static void initReleaseEnvironment() {
        if (envitonment != null) {
            throw new RuntimeException("AppEnvironment can only init once！");
        }
        envitonment = ENVITONMENT_RELEASE;
    }

    /**
     * 是否预发环境
     *
     * @return true 预发环境   false 不是预发环境
     */
    public static boolean isPreReleaseEnvironment() {
        if (envitonment == null) {
            throw new RuntimeException("you must init AppEnvironment first");
        }
        return envitonment == ENVITONMENT_PRE_RELEASE;
    }

    /**
     * 是否测试环境
     *
     * @return true 测试环境   false 不是测试环境
     */
    public static boolean isDebugEnvironment() {
        if (envitonment == null) {
            throw new RuntimeException("you must init AppEnvironment first");
        }
        return envitonment == ENVITONMENT_DEBUG;
    }

    /**
     * 是否线上环境
     *
     * @return true 线上环境   false 不是线上环境
     */
    public static boolean isReleaseEnvironment() {
        if (envitonment == null) {
            throw new RuntimeException("you must init AppEnvironment first");
        }
        return envitonment == ENVITONMENT_RELEASE;
    }


}
