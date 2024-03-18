package com.mini.emoti.config.error;

import org.springframework.http.HttpStatus;

import com.mini.emoti.config.constant.ExceptionConstant;

import lombok.Getter;

@Getter
public class EmotionException extends Exception{
    
    private HttpStatus httpStatus;

    public EmotionException(ExceptionConstant exceptionConstant){
        super(exceptionConstant.getErrorMsg());
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
