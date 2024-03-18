package com.mini.emoti.config.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

// @RestControllerAdvice 어노테이션을 사용해 전역에서 발생하는 예외를 처리하는 예외 핸들러 컨트롤러
//  애플리케이션 전반에 걸쳐 발생할 수 있는 예외들을 효과적으로 관리하고, 에러 발생 시 일관된 응답 구조를 제공

@Slf4j
@RestControllerAdvice
public class CommonRestAdvice {

    
    @ExceptionHandler(value = EmotionException.class)
    public ResponseEntity<Map<String, Object>> EmotiExceptionHandler(EmotionException e){

        log.info("[CommonRestAdvice][ExceptionHandler] Start");
        log.info("exception message:"+e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = e.getHttpStatus();

        Map<String,Object> error_msg = new HashMap<>();
        error_msg.put("message", "잘못된 요청을 하셨습니다. 다시 확인해주세요.");
        error_msg.put("exception message", e.getMessage());

        return new ResponseEntity<>(error_msg, headers, status);

    }

    // 가장 일반적인 Exception 클래스를 처리
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> defaultExceptionHandler(Exception e){
        log.info("[CommonRestAdvice][defaultExceptionHandler] Start");
        log.info("exception message: "+e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String,Object> error_msg = new HashMap<>();
        error_msg.put("message", "에러가 발생했습니다.");
        error_msg.put("[Exception]", e.getMessage());

        return new ResponseEntity<>(error_msg, headers, status);

    }


}
