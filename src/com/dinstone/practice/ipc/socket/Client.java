
package com.dinstone.practice.ipc.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
            // socket.setTcpNoDelay(true);

            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            for (int i = -1; i < 10; i++) {
                System.out.println("client send: " + i);
                outStream.write(i);
                // outStream.flush();

                int k = inStream.read();
                System.out.println("client received: " + k);

                Thread.sleep(1000);
            }

            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
