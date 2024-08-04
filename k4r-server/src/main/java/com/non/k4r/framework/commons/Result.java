package com.non.k4r.framework.commons;

import com.non.k4r.framework.constant.ErrorCodes;

import java.util.HashMap;

/**
 * @author michael
 */
public class Result<T> extends HashMap<String, Object> {

    private static final String ATTR_ERROR_CODE = "errorCode";
    private static final String ATTR_MESSAGE = "message";
    private static final String ATTR_DATA = "data";


    private Result(int errorCode) {
        put(ATTR_ERROR_CODE, errorCode);
    }


    public static <T> Result<T> success() {
        return new Result<>(0);
    }

    public static <T> Result<T> create(boolean success) {
        if (success) {
            return success();
        } else {
            return fail(ErrorCodes.UNKNOWN_ERROR);
        }
    }

    public static <T> Result<T> success(Object data) {
        return success(ATTR_DATA, data);
    }

    public static <T> Result<T> success(String key, Object value) {
        Result<T> ret = new Result<>(0);
        ret.put(key, value);
        return ret;
    }

    public static <T> Result<T> fail() {
        return fail(ErrorCodes.UNKNOWN_ERROR);
    }

    public static <T> Result<T> fail(ErrorCodes errorCode) {
        return fail(errorCode.getErrorCode(), errorCode.getMessage());
    }

    public static <T> Result<T> fail(int errorCode, String errorMessage) {
        Result<T> result = new Result<>(errorCode);
        result.put(ATTR_MESSAGE, errorMessage);
        return result;
    }


    public int failCode() {
        return get(ATTR_ERROR_CODE);
    }

    public void failCode(int errorCode) {
        this.put(ATTR_ERROR_CODE, errorCode);
    }

    public String failMessage() {
        return this.get(ATTR_MESSAGE);
    }

    public void failMessage(String errorMessage) {
        put(ATTR_MESSAGE, errorMessage);
    }

    public Object data() {
        return get(ATTR_DATA);
    }

    public void data(Object data) {
        put(ATTR_DATA, data);
    }

    public <T> T get(String key) {
        return (T) super.get(key);
    }

    public Result<T> set(String key, Object value) {
        put(key, value);
        return this;
    }

    public Result<T> setIfNotNull(String key, Object value) {
        if (value != null && key != null) {
            put(key, value);
        }
        return this;
    }

    public boolean isSuccess() {
        return failCode() == 0;
    }

}
