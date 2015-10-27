
package com.dinstone.practice.jsa.demo;

import java.security.AccessControlContext;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Set;

import javax.security.auth.Subject;

public class PayrollAction implements PrivilegedAction<Integer> {

    public Integer run() {
        // Get the passed in subject from the DoAs
        AccessControlContext context = AccessController.getContext();
        Subject subject = Subject.getSubject(context);
        if (subject == null) {
            throw new AccessControlException("Denied");
        }
        //
        // Iterate through the principal set looking for joeuser. If
        // he is not found,
        Set<Principal> principals = subject.getPrincipals();
        for (Principal principal : principals) {
            if (principal.getName().equals("joeuser")) {
                System.out.println("joeuser has Payroll access\n");
                return new Integer(0);
            }
        }

        throw new AccessControlException("Denied");
    }

}
