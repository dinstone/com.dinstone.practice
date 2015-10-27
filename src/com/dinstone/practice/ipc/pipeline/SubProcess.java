
package com.dinstone.practice.ipc.pipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubProcess {

    public static void main(String[] args) throws IOException {
        System.out.println("SubProcess is running");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line == null || line.length() == 0) {
                break;
            }

            System.out.println(line);
        }
    }

}
