package com.mini.emoti.config.error;
import com.mini.emoti.config.constant.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {

    private ErrorCode errorCode;
    private String message;

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
