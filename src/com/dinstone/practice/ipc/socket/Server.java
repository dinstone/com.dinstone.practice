
package com.dinstone.practice.ipc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5555);
            Socket socket = server.accept();
            socket.setTcpNoDelay(true);

            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                int k = inStream.read();
                System.out.println("server received: " + k);

                outStream.write(2 * k);
                outStream.flush();
                System.out.println("server send: " + 2 * k);
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
