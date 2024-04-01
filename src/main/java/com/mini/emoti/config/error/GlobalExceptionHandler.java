package com.mini.emoti.config.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mini.emoti.config.constant.ErrorCode;

import lombok.extern.slf4j.Slf4j;

import static com.mini.emoti.config.constant.ErrorCode.*;


// @RestControllerAdvice 어노테이션을 사용해 전역에서 발생하는 예외를 처리하는 예외 핸들러 컨트롤러
//  애플리케이션 전반에 걸쳐 발생할 수 있는 예외들을 효과적으로 관리하고, 에러 발생 시 일관된 응답 구조를 제공

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //  숫자 대신에 문자를 입력
    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //  SpringBoot의 validation 라이브러리의 @Valid를 사용헀을 때 검증이 실패하면 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException e) {
    ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
    BindingResult bindingResult = e.getBindingResult();

    StringBuilder builder = new StringBuilder();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
        builder.append("[");
        builder.append(fieldError.getField());
        builder.append("](은)는 ");
        builder.append(fieldError.getDefaultMessage());
        builder.append(". ");
    }
    builder.deleteCharAt(builder.length() - 1);

    final ErrorResponse response = new ErrorResponse(errorCode.getCode(), builder.toString());    
    return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
}
    // 비즈니스 요구사항에 따른 Exception
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    // 그 밖에 발생하는 모든 예외처리가 이곳으로 모인다.
    @ExceptionHandler(Exception.class)
        protected ResponseEntity<ErrorResponse> handleException(Exception e) {
            ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            ErrorResponse response = new ErrorResponse(errorCode.getCode(), e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
        }




}
