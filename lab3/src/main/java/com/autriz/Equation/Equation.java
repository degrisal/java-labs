package com.autriz.Equation;

import java.io.Serializable;

public class Equation implements Serializable {
    // static final long serialVersionUID = 425563L;

    public Number lhs;
    public Number rhs;
    public Operation operation;

    public Equation(Number lhs, Number rhs, Operation operation) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operation = operation;
    } 

    public static Equation from(String string) {
        String[] vals = string.split("[+-/*^%]");

        if (vals.length > 2 || vals.length < 2) 
            throw new RuntimeException("Invalid expression");
            
        String operationString = string.substring(vals[0].length(), vals[0].length() + 1); 

        Operation operation;

        switch (operationString) {
            case "+": {
                operation = Operation.ADD;
                break;
            }
            case "-": {
                operation = Operation.SUBTRACT;
                break;
            }
            case "*": {
                operation = Operation.MULTIPLY;
                break;
            }
            case "/": {
                operation = Operation.DIVIDE;
                break;
            }
            case "%": {
                operation = Operation.REMAINDER;
                break;
            }
            case "^": {
                operation = Operation.POWER;
                break;
            }
            default:
                throw new RuntimeException("Invalid operator");
        }

        return new Equation(
            Double.parseDouble(vals[0]), 
            Double.parseDouble(vals[1]), 
            operation
        );
    }
}
