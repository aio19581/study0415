package org.example.service;

import org.example.model.Input;
import org.example.model.Output;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public Output calculate(Input input) {
        int a = input.getNumA();
        int b = input.getNumB();

        int result = switch (input.getOperator()) {
            case PLUS -> a + b;
            case MINUS -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a / b;
            case POWER -> (int)Math.pow(a,b);
        };

        return new Output(a, b, input.getOperator(), result);
    }
}
