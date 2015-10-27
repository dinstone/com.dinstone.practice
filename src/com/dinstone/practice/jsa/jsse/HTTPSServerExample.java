
package com.dinstone.practice.jsa.jsse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * -Djavax.net.ssl.keyStore=C:\Users\guojf\sslKeyStore
 * -Djavax.net.ssl.keyStorePassword=123456
 * 
 * @author guojf
 * @version 1.0.0.2014-3-10
 */
public class HTTPSServerExample {

    public static void main(String[] args) throws IOException {

        //
        // create an SSL socket using the factory and pick port 8080
        SSLServerSocketFactory sslsf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket ss = sslsf.createServerSocket(8080);
        //
        // loop forever
        while (true) {
            try {
                //
                // block waiting for client connection
                Socket s = ss.accept();
                System.out.println("Client connection made");
                // get client request
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                System.out.println(in.readLine());
                //
                // make an HTML response
                PrintWriter out = new PrintWriter(s.getOutputStream());
                out.println("<HTML><HEAD><TITLE>HTTPS Server Example</TITLE>"
                        + "</HEAD><BODY><H1>Hello World!</H1></BODY></HTML>\n");
                //
                // Close the stream and socket
                out.close();
                s.close();
            } catch (SSLException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
