
package com.dinstone.practice.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.TreeSet;

public class AddressSort {

    /**
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        addressSort();

        String inFile = "address.txt";
        String outFile = "sorted.txt";
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            String temp = null;
            Set<AddressNode> anlist = new TreeSet<AddressSort.AddressNode>();
            while ((temp = reader.readLine()) != null) {
                String[] tokens = temp.split(",");
                AddressNode node = new AddressNode(tokens[0].trim(), tokens[1].trim(), tokens[2].trim());
                anlist.add(node);
            }

            System.out.println("=======================================");
            for (AddressNode node : anlist) {
                // StringBuilder line = new StringBuilder();
                String line = node.toString();
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }

            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }

    }

    private static void addressSort() throws UnknownHostException {
        byte[] oth = InetAddress.getByName("112.124.3.226").getAddress();
        byte[] obj = InetAddress.getByName("112.124.3.4").getAddress();

        int f = 0;
        for (int i = 0; i < obj.length; i++) {
            System.out.println("obj[" + i + "]=" + obj[i] + ",oth[" + i + "]=" + (oth[i] & 0xff));
            if (obj[i] != oth[i]) {
                f = obj[i] - oth[i];
            }
        }

        System.out.println(f);
    }

    static class AddressNode implements Comparable<AddressNode> {

        String outIp;

        String inIp;

        String password;

        public AddressNode(String outIp, String inIp, String password) {
            super();
            this.outIp = outIp;
            this.inIp = inIp;
            this.password = password;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return outIp + "\t" + inIp + "\t" + password;
        }

        public int compareTo(AddressNode o) {
            try {
                byte[] oth = InetAddress.getByName(o.outIp).getAddress();
                byte[] obj = InetAddress.getByName(this.outIp).getAddress();
                for (int i = 0; i < obj.length; i++) {
                    int a = (obj[i] & 0xff);
                    int b = (oth[i] & 0xff);
                    if (a != b) {
                        return a - b;
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

}
