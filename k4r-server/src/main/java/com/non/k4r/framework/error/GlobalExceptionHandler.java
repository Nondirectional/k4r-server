package com.non.k4r.framework.error;

import com.non.k4r.framework.commons.Result;
import com.non.k4r.framework.constant.CustomException;
import com.non.k4r.framework.constant.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public Result<Void> handleThrowable(Throwable e) {
        log.error("errors:{}", e.getMessage(), e);
        return Result.fail(ErrorCodes.UNKNOWN_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("errors:{}", e.getMessage(), e);
        return Result.fail(ErrorCodes.UNKNOWN_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Result<Void> handleNoSuchElementException() {
        return Result.fail(ErrorCodes.NO_SUCH_ELEMENT);
    }

    @ExceptionHandler(CustomException.class)
    public Result<Void> handleCustomException(CustomException e) {
        if (StringUtils.hasText(e.getLogMessage())) {
            log.error("handle exception,error message:{}", e.getLogMessage(), e);
        }

        if (StringUtils.hasText(e.getRemindMessage())) {
            return Result.fail(e.getErrorCode().getErrorCode(), e.getRemindMessage());
        } else {
            return Result.fail(e.getErrorCode());
        }
    }

}
