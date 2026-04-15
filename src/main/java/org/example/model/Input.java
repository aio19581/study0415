package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Input {
    private int numA;
    private int numB;
    private Operator operator; //기호
}
