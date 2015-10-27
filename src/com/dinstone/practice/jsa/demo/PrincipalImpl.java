
package com.dinstone.practice.jsa.demo;

import java.io.Serializable;
import java.security.Principal;

public class PrincipalImpl implements Principal, Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String name;

    public PrincipalImpl(String n) {
        name = n;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PrincipalImpl)) {
            return false;
        }
        PrincipalImpl pobj = (PrincipalImpl) obj;
        if (name.equals(pobj.getName())) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

}