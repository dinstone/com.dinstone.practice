
package com.dinstone.practice.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MemcachedTest {

    public static void main(String[] args) throws UnknownHostException, IOException {
        if (args.length != 2) {
            System.err.println("Usage: <host> <port>");
            System.exit(2);
        }

        InetAddress address = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);
        showAddress(address);

        Socket socket = new Socket(address, port);
        socket.setKeepAlive(true);
        InetAddress ipAdd = socket.getInetAddress();
        System.out.println("KeepAlive = " + socket.getKeepAlive());
        showAddress(ipAdd);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        doAction(in, out);

        assertSorcketStatus(socket);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Escape character is 'quit'.");
            String cmd = reader.readLine();
            if ("quit".equals(cmd)) {
                break;
            }
        }

        doAction(in, out);

        socket.shutdownOutput();
        assertSorcketStatus(socket);
        socket.shutdownInput();

        socket.close();
        assertSorcketStatus(socket);
    }

    private static void doAction(InputStream in, OutputStream out) {
        System.out.println("============================================");
        try {
            out.write("version\r\n".getBytes());
            out.flush();

            byte[] b = new byte[1024];
            int n = 0;
            while ((n = in.read(b)) != -1) {
                String x = new String(b, 0, n);
                System.out.println(x);
                if (x.endsWith("\r\n")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("============================================");
    }

    private static void assertSorcketStatus(Socket socket) {
        System.out.println("isInputShutdown = " + socket.isInputShutdown());
        System.out.println("isOutputShutdown = " + socket.isOutputShutdown());

        if (socket.isClosed() == false && socket.isConnected() == true) {
            System.out.println("Connection opened.");
        } else {
            System.out.println("Connection closed.");
        }
    }

    private static void showAddress(InetAddress ipAdd) {
        if (ipAdd instanceof Inet4Address) {
            System.out.print("IPV4 Address:");
        } else {
            System.out.print("IPV6 Address:");
        }
        System.out.println(ipAdd.getHostAddress());
    }
}
