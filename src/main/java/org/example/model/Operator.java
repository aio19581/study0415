package org.example.model;

import lombok.Getter;

@Getter
public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POWER("^");

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

}
