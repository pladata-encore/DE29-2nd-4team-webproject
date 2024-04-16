package com.mini.emoti.config.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mini.emoti.config.constant.error.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;


// https://velog.io/@leehyeonmin34/exception-handling
// https://cheese10yun.github.io/spring-jpa-best-02/

/*스프링의 @ControllerAdvice라는 걸 쓰면 모든 컨트롤러(에서 호출한 어떤 메서드든)에 대해 글로벌한 설정을 할 수 있게됩니다. 
* 특정 컨트롤러 내에 @ExceptionHandler라는 걸 사용하면 특정 컨트롤러의 예외처리하는 로직을 메서드 형태로 명시할 수 있습니다.
*/

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        log.info("[ControllerExceptionHandler][handleMethodArgumentNotValidException] fieldErrors : "+fieldErrors);
        return buildFieldErrors(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
    }

    /**
     * @ModelAttribute 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBindException(org.springframework.validation.BindException e) {
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        log.info("[ControllerExceptionHandler][handleBindException] fieldErrors : "+fieldErrors);

        return buildFieldErrors(ErrorCode.INVALID_INPUT_VALUE, fieldErrors);
    }

    

    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.parallelStream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .reason(error.getDefaultMessage())
                        .field(error.getField())
                        .value((String) error.getRejectedValue())
                        .build())
                .collect(Collectors.toList());
    }


    private ErrorResponse buildFieldErrors(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }


}
