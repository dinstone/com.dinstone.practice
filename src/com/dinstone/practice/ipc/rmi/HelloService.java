
package com.dinstone.practice.ipc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {

    public String sayHello(String name) throws RemoteException;
}
