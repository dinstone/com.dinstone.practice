
package com.dinstone.practice.ipc.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    /**  */
    private static final long serialVersionUID = 1L;

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    public String sayHello(String name) {
        return "Hello " + name;
    }

}
