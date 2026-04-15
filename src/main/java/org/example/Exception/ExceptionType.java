package org.example.Exception;

import lombok.Getter;

@Getter
public enum ExceptionType {

    INTEGER_OVERFLOW("입력가능한 값이 아닙니다."),
    INVALID_OPERATOR("잘못된 연산자 입니다.");

    private final String message;

    ExceptionType(String message){
        this.message = message;
    }

}
