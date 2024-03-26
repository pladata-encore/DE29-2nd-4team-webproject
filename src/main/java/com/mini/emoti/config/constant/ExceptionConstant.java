package com.mini.emoti.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionConstant {

    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    UNAUTHORIZED_ACCESS("접근 권한이 없습니다.");

    private final String errorMsg;
}
