package com.non.k4r.framework.constant;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    NONE(0, "成功"),
    UNKNOWN_ERROR(1, "未知错误"),
    PARAMETER_ERROR(2, "参数错误"),
    ACCESS_TOKEN_INVALID(5, "AccessToken无效"),
    ACCESS_TOKEN_NOT_FOUND(7, "AccessToken未找到"),
    ACCESS_TOKEN_EMPTY(8, "AccessToken为空"),
    NO_SUCH_ELEMENT(9, "找不到目标资源"),
    INCORRECT_PASSWORD(10, "密码错误"),
    ALREADY_EXISTS(11, "资源已存在");

    private final int errorCode;
    private final String message;

    ErrorCodes(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
