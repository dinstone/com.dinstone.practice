
package com.dinstone.practice.ipc.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        LocateRegistry.createRegistry(1099);

        Remote obj = new HelloServiceImpl();
        Naming.rebind("rmi://localhost:1099/helloService", obj);

        System.out.println("deployment hello service");
    }
}
