package com.autriz.Client;

import java.io.Console;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.autriz.Calculator.ICalculator;
import com.autriz.Equation.Equation;

public class Client {

    public static final String UNIQUE_BINDING_NAME = "server.calculator";

    public static void main(String[] args) throws RemoteException, NotBoundException {
       final Registry registry = LocateRegistry.getRegistry(2732);

       ICalculator calculator = (ICalculator) registry.lookup(UNIQUE_BINDING_NAME);

       boolean running = true;

       String input = prompt("Equation (type 'q' or 'quit' to exit):");
       while (running) {
            switch (input) {
                case("q"):
                case("quit"): { running = false; continue; }
                default: {
                    try {
                        performCalculus(calculator, input);
                    }
                    catch (Exception e) {
                        System.out.println(String.format("Error: %s", e.toString()));
                    } 
                    input = prompt("Equation: ");
                }
            }
       }
    }
    
    public static void performCalculus(ICalculator calculator, String input) throws RemoteException, RuntimeException {
        Equation equation = Equation.from(input);

        double result = calculator.calculate(equation);
    
        System.out.println(String.format("Result: %.3f", result));
    }

    public static String prompt(String question) {
        System.out.println(question);
        
        Console console = System.console();

        String answer = console.readLine();

        return answer;
    }

}
