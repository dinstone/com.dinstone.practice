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

import java.net.Socket;

public class CloseConnection {

    public static void printState(Socket socket, String name) {
        /*
         * 使用isClosed方法所返回的只是Socket对象的当前状态，也就是说， 不管Socket对象是否曾经连接成功过，只要处于关闭状态，
         * isClosde就返回true。如果只是建立一个未连接的Socket对象，isClose也同样返回true。
         */
        System.out.println(name + ".isClosed():" + socket.isClosed());
        /*
         * isConnected方法所判断的并不是Socket对象的当前连接状态，而是Socket对象是否曾经连接成功过，如果成功连接过，
         * 即使现在isClose返回true，isConnected仍然返回true
         */
        System.out.println(name + ".isConnected():" + socket.isConnected());

        // 要判断当前的Socket对象是否处于连接状态，必须同时使用isClose和isConnected方法，
        // 即只有当isClose返回false，isConnected返回true的时候Socket对象才处于连接状态。
        if (socket.isClosed() == false && socket.isConnected() == true) {
            System.out.println(name + "处于连接状态!");
        } else {
            System.out.println(name + "处于非连接状态!");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Socket socket1 = null, socket2 = null;

        socket1 = new Socket("172.17.6.39", 8080);
        printState(socket1, "socket1");

        socket1.getOutputStream().close();
        printState(socket1, "socket1");

        socket2 = new Socket();
        printState(socket2, "socket2");

        socket2.close();
        printState(socket2, "socket2");
    }
}