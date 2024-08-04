package com.non.k4r.framework.constant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private ErrorCodes errorCode;
    private String remindMessage;
    private String logMessage;

    public CustomException(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCodes errorCode, String remindMessage) {
        this(errorCode);
        this.remindMessage = remindMessage;
    }

    public CustomException(ErrorCodes errorCode, String remindMessage, String logMessage) {
        this(errorCode, remindMessage);
        this.logMessage = logMessage;
    }
}
