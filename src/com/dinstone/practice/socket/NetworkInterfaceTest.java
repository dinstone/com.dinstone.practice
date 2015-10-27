/*
 * Copyright (C) 2011~2012 dinstone <dinstone@163.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.dinstone.practice.socket;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class NetworkInterfaceTest {

    public static void main(String[] args) {
        List<InetAddress> al = getHostAddressList();
        System.out.println(al);

        System.out.println("********************************");

        networkInterface();
    }

    private static void networkInterface() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                System.out.println(ni.getName() + " : " + ni.getDisplayName());
                System.out.println("MAC \t:" + ni.getHardwareAddress());
                System.out.println("MTU \t:" + ni.getMTU());
                System.out.println("Loopback \t:" + ni.isLoopback());
                System.out.println("PTP \t:" + ni.isPointToPoint());
                System.out.println("UP \t:" + ni.isUp());
                System.out.println("Virtual \t:" + ni.isVirtual());

                showIpAddress(ni);

                System.out.println("=============================");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private static List<InetAddress> getHostAddressList() {
        List<InetAddress> ipas = new LinkedList<InetAddress>();
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> ads = ni.getInetAddresses();
                while (ads.hasMoreElements()) {
                    java.net.InetAddress ipAdd = ads.nextElement();
                    System.out.println("ipAdd = " + ipAdd);
                    if (ipAdd.isSiteLocalAddress() && !ipAdd.isLoopbackAddress() && (ipAdd instanceof Inet4Address)) {
                        ipas.add(ipAdd);
                    }
                }
            }
        } catch (Exception e) {

        }

        return ipas;
    }

    private static void showIpAddress(NetworkInterface ni) {
        Enumeration<InetAddress> ads = ni.getInetAddresses();
        while (ads.hasMoreElements()) {
            java.net.InetAddress ipAdd = ads.nextElement();
            if (ipAdd instanceof Inet4Address) {
                System.out.print("IPV4 Address:");
            } else {
                System.out.print("IPV6 Address:");
            }
            System.out.println(ipAdd.getHostAddress());
        }
    }

}
