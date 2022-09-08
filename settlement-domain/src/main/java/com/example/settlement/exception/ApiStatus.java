package com.example.settlement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiStatus {
    NOT_FOUND_SETTLEMENT(HttpStatus.NOT_FOUND, -600, "해당 정산리스트를 찾을수 없습니다."),
    INVALID_MODIFY_SETTLEMENT(HttpStatus.BAD_REQUEST, -601, "정산내역이 없는 사업장입니다."),
    INVALID_STATUS_SETTLEMENT(HttpStatus.BAD_REQUEST, -602, "현재 승인요청중 상태가 아닙니다.")
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
