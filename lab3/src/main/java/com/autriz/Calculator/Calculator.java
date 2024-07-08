package com.autriz.Calculator;

import java.rmi.RemoteException;

import com.autriz.Equation.Equation;

public class Calculator implements ICalculator {

    @Override
    public double multiply(double x, double y) throws RemoteException {
        return x * y;
    } 

    @Override
    public double divide(double x, double y) throws RemoteException {
        return x / y;
    }

    @Override
    public double add(double x, double y) throws RemoteException {
        return x + y;
    }

    @Override
    public double subtract(double x, double y) throws RemoteException {
        return x - y;
    }

    @Override
    public double remainder(double x, double y) throws RemoteException {
        return x % y;
    }

    @Override
    public double power(double x, double y) throws RemoteException {
        return Math.pow(x, y);
    }

    public double calculate(Equation equation) throws RemoteException {
        double result;

        switch (equation.operation) {
            case ADD: {
                result = this.add(
                    equation.lhs.doubleValue(), 
                    equation.rhs.doubleValue()
                );
                break;
            }
            case SUBTRACT: {
                result = this.subtract(
                    equation.lhs.doubleValue(), 
                    equation.rhs.doubleValue()
                );
                break;
            }
            case MULTIPLY: {
                result = this.multiply(
                    equation.lhs.doubleValue(), 
                    equation.rhs.doubleValue()
                );
                break;
            }
            case DIVIDE: {
                result = this.divide(
                    equation.lhs.doubleValue(), 
                    equation.rhs.doubleValue()
                );
                break;
            }
            case REMAINDER: {
                result = this.remainder(
                    equation.lhs.doubleValue(), 
                    equation.rhs.doubleValue()
                );
                break;
            }
            default: {
                throw new RuntimeException("JAVA WTF");
            }
        }

        return result;
    }

}
