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

import java.net.InetSocketAddress;
import java.net.Socket;

public class CloseConnection1 {

    public static void printState(Socket socket) {
        System.out.println("isInputShutdown:" + socket.isInputShutdown());
        System.out.println("isOutputShutdown:" + socket.isOutputShutdown());
        System.out.println("isClosed:" + socket.isClosed());
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("10.1.251.240", 80);
        printState(socket);

        socket.shutdownInput();
        printState(socket);

        socket.shutdownOutput();
        printState(socket);

        socket.close();
        printState(socket);

        socket.connect(new InetSocketAddress("10.1.251.240", 80));
        printState(socket);
    }
}