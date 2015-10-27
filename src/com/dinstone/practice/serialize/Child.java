
package com.dinstone.practice.serialize;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class Child implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    // serialVersionUID = -8225953875870532045

    private String name;

    // private int age;

    public Child() {
    }

    public Child(String name) {
        super();
        this.name = name;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Child [name=" + name + "]";
    }

    /**
     * the name to get
     * 
     * @return the name
     * @see Child#name
     */
    public String getName() {
        return name;
    }

    /**
     * the name to set
     * 
     * @param name
     * @see Child#name
     */
    public void setName(String name) {
        this.name = name;
    }

    // private void readObject(java.io.ObjectInputStream in) throws IOException,
    // ClassNotFoundException {
    //
    // }
    //
    // private void writeObject(java.io.ObjectOutputStream out) throws
    // IOException {
    //
    // }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
    }

}
