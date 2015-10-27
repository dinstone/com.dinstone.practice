
package com.dinstone.practice.jmx;

public interface EchoMBean {

    public void print(String yourName);

    public String getMyName();

    public String getReverseName(String yourName);
}
