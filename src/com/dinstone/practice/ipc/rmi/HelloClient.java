
package com.dinstone.practice.ipc.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClient {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        HelloService service = (HelloService) Naming.lookup("rmi://localhost:1099/helloService");
        String hi = service.sayHello("rmi");

        System.out.println(hi);
    }
}
