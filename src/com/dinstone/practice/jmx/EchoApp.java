
package com.dinstone.practice.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class EchoApp {

    /**
     * @param args
     * @throws NullPointerException
     * @throws MalformedObjectNameException
     */
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.dinstone.practice.jmx:host=dinstone,ren=xx,type=Echo");
        Echo mb = new Echo("dinstone");
        mbs.registerMBean(mb, name);

        // mbs.setAttribute(name, new Attribute("myName", mb));

        mbs.invoke(name, "print", new Object[] { "td.ad" }, new String[] { "java.lang.String" });

        Thread.sleep(10000000);
    }

}
