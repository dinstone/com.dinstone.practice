
package com.dinstone.practice.jsa.demo;

import java.security.BasicPermission;

public class PersonnelPermission extends BasicPermission {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public PersonnelPermission(String name) {
        super(name);
    }

    public PersonnelPermission(String name, String action) {
        super(name);
    }
}
