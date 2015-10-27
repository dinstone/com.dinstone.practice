
package com.dinstone.practice.jsa.demo;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class AlwaysLoginModule implements LoginModule {

    private Subject subject;

    private Principal principal;

    private CallbackHandler callbackHandler;

    private String username;

    private boolean loginSuccess;

    //
    // Initialize sets up the login module. sharedState and options are
    // advanced features not used here
    public void initialize(Subject sub, CallbackHandler cbh, Map sharedState, Map options) {
        subject = sub;
        callbackHandler = cbh;
        loginSuccess = false;
    }

    //
    // The login phase gets the userid from the user
    public boolean login() throws LoginException {
        //
        // Since we need input from a user, we need a callback handler
        if (callbackHandler == null) {
            throw new LoginException("No CallbackHandler defined");

        }
        Callback[] callbacks = new Callback[1];
        callbacks[0] = new NameCallback("Username");
        //
        // Call the callback handler to get the username
        try {
            System.out.println("\nAlwaysLoginModule Login");
            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
        } catch (IOException ioe) {
            throw new LoginException(ioe.toString());
        } catch (UnsupportedCallbackException uce) {
            throw new LoginException(uce.toString());
        }
        loginSuccess = true;
        System.out.println();
        System.out.println("Login: AlwaysLoginModule SUCCESS");
        return true;
    }

    //
    // The commit phase adds the principal if both the overall authentication
    // succeeds (which is why commit was called) as well as this particular
    // login module
    public boolean commit() throws LoginException {
        //
        // Check to see if this login module succeeded (which it always will

        // in this example)
        if (loginSuccess == false) {
            System.out.println("Commit: AlwaysLoginModule FAIL");
            return false;
        }
        //
        // If this login module succeeded too, then add the new principal
        // to the subject (if it does not already exist)
        principal = new PrincipalImpl(username);

        if (!(subject.getPrincipals().contains(principal))) {
            subject.getPrincipals().add(principal);
        }
        System.out.println("Commit: AlwaysLoginModule SUCCESS");
        return true;
    }

    //
    // The abort phase is called if the overall authentication fails, so
    // we have to clean up the internal state
    public boolean abort() throws LoginException {

        if (loginSuccess == false) {
            System.out.println("Abort: AlwaysLoginModule FAIL");
            principal = null;
            return false;
        }
        System.out.println("Abort: AlwaysLoginModule SUCCESS");
        logout();

        return true;
    }

    //
    // The logout phase cleans up the state
    public boolean logout() throws LoginException {

        subject.getPrincipals().remove(principal);
        loginSuccess = false;
        principal = null;
        System.out.println("Logout: AlwaysLoginModule SUCCESS");
        return true;
    }
}
