package com.fastcome1985.learn;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by luojianxiang on 17/5/26.
 */
public class JSONUtils {

    private static final Gson gson = new Gson();

    /**
     * JSON to object
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            // TODO
            return null;
        }
    }

    /**
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJSON(String json, Type type) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }
}
