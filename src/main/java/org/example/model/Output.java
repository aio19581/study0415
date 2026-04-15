package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Output {
    private int numA;
    private int numB;
    private Operator operator;
    private int result;

    @Override
    public String toString() {
        return numA + " " + operator.getOperator() + " " + numB + " = " + result;
    }
}
