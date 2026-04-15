package org.example.Exception;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionType type;

    public CustomException (ExceptionType type){
        super(type.getMessage());
        this.type = type;
        //RuntimeException 클래스 생성자 호출
    }
}
