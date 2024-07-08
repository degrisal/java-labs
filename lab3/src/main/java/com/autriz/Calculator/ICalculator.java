package com.autriz.Calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.autriz.Equation.Equation;

public interface ICalculator extends Remote {
    double multiply(double x, double y) throws RemoteException;
    double divide(double x, double y) throws RemoteException;
    double add(double x, double y) throws RemoteException;
    double subtract(double x, double y) throws RemoteException;
    double remainder(double x, double y) throws RemoteException;
    double power(double x, double y) throws RemoteException;
    double calculate(Equation equation) throws RemoteException;
}
