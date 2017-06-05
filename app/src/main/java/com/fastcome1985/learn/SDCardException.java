package com.fastcome1985.learn;

/**
 * Created by luojianxiang on 17/5/26.
 */

public class SDCardException extends RuntimeException {

    public SDCardException(String sn, String message, Throwable cause) {
        super("sn:" + sn + "  " + message, cause);
    }

}
