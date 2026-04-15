package org.example.model;

import lombok.Getter;

@Getter
public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

}
