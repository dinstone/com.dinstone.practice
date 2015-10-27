
package com.dinstone.practice.jsa.demo;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class PasswordLoginModule implements LoginModule {

    private Subject subject;

    private Principal principal;

    private CallbackHandler callbackHandler;

    private String username;

    private char[] password;

    private boolean loginSuccess;

    //
    // Initialize sets up the login module. sharedState and options are
    // advanced features not used here
    public void initialize(Subject sub, CallbackHandler cbh, Map sharedState, Map options) {

        subject = sub;
        callbackHandler = cbh;
        loginSuccess = false;
        username = null;
        clearPassword();
    }

    //
    // The login phase gets the userid and password from the user and
    // compares them to the hardcoded values "joeuser" and "joeuserpw".
    public boolean login() throws LoginException {
        //
        // Since we need input from a user, we need a callback handler
        if (callbackHandler == null) {
            throw new LoginException("No CallbackHandler defined");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username");
        callbacks[1] = new PasswordCallback("Password", false);
        //
        // Call the callback handler to get the username and password
        try {
            System.out.println("\nPasswordLoginModule Login");
            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            char[] temp = ((PasswordCallback) callbacks[1]).getPassword();
            password = new char[temp.length];
            System.arraycopy(temp, 0, password, 0, temp.length);
            ((PasswordCallback) callbacks[1]).clearPassword();
        } catch (IOException ioe) {
            throw new LoginException(ioe.toString());
        } catch (UnsupportedCallbackException uce) {
            throw new LoginException(uce.toString());
        }
        System.out.println();
        //
        // If username matches, go on to check password
        if ("joeuser".equals(username)) {
            System.out.println("Login: PasswordLoginModule Username Matches");
            if (password.length == 5 && password[0] == 'j' && password[1] == 'o' && password[2] == 'e'
                    && password[3] == 'p' && password[4] == 'w') {
                //
                // If userid and password match, then login is a success
                System.out.println("Login: PasswordLoginModule Password Matches");
                loginSuccess = true;
                System.out.println("Login: PasswordLoginModule SUCCESS");
                clearPassword();
                return true;
            } else {
                System.out.println("Login: PasswordLoginModule Password Mismatch");
            }
        } else {
            System.out.println("Login: PasswordLoginModule Username Mismatch");
        }
        //
        // If either mismatch, then this login module fails

        loginSuccess = false;
        username = null;
        clearPassword();
        System.out.println("Login: PasswordLoginModule FAIL");
        throw new FailedLoginException();
    }

    //
    // The commit phase adds the principal if both the overall
    // authentication succeeds (which is why commit was called)
    // as well as this particular login module
    public boolean commit() throws LoginException {

        //
        // Check to see if this login module succeeded
        if (loginSuccess == false) {
            System.out.println("Commit: PasswordLoginModule FAIL");
            return false;
        }
        // If this login module succeeded too, then add the new principal
        // to the subject (if it does not already exist)
        principal = new PrincipalImpl(username);

        if (!(subject.getPrincipals().contains(principal))) {
            subject.getPrincipals().add(principal);
        }
        username = null;
        System.out.println("Commit: PasswordLoginModule SUCCESS");
        return true;
    }

    //
    // The abort phase is called if the overall authentication fails, so
    // we have to cleanup the internal state
    public boolean abort() throws LoginException {

        if (loginSuccess == false) {
            System.out.println("Abort: PasswordLoginModule FAIL");
            principal = null;
            username = null;
            return false;
        }
        System.out.println("Abort: PasswordLoginModule SUCCESS");
        logout();
        return true;
    }

    //
    // The logout phase cleans up the state
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(principal);
        loginSuccess = false;
        username = null;
        principal = null;
        System.out.println("Logout: PasswordLoginModule SUCCESS");
        return true;
    }

    //
    // Private helper function to clear the password, a good programming
    // practice
    private void clearPassword() {
        if (password == null) {
            return;
        }
        for (int i = 0; i < password.length; i++) {
            password[i] = ' ';
        }
        password = null;
    }
}