
package com.dinstone.practice.jsa.demo;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class PersonnelAction implements PrivilegedAction<Integer> {

    public Integer run() {
        AccessController.checkPermission(new PersonnelPermission("access"));
        System.out.println("Subject has Personnel access\n");
        return new Integer(0);
    }

}
