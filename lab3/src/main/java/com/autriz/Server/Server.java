package com.autriz.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.autriz.Calculator.Calculator;

public class Server {

   public static final String UNIQUE_BINDING_NAME = "server.calculator";

   public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

       final Calculator server = new Calculator();

       final Registry registry = LocateRegistry.createRegistry(2732);

       Remote stub = UnicastRemoteObject.exportObject(server, 0);
       registry.bind(UNIQUE_BINDING_NAME, stub);

       Thread.sleep(Integer.MAX_VALUE);

   }
}
