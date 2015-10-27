
package com.dinstone.practice.jmx;

public class Echo implements EchoMBean {

    private String myName;

    public Echo(String myName) {
        super();
        this.myName = myName;
    }

    public void print(String yourName) {
        System.out.println("Hi " + yourName + "! I'm " + myName);
    }

    /**
     * the myName to get
     * 
     * @return the myName
     * @see Echo#myName
     */
    public String getMyName() {
        return myName;
    }

    /**
     * the myName to set
     * 
     * @param myName
     * @see Echo#myName
     */
    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getReverseName(String yourName) {
        return new StringBuilder(yourName).reverse().toString();
    }

}
