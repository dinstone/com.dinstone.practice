
package com.dinstone.practice.redblue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class RedBlueDataUpdate {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://ledysys.com/LottoData/ssq.txt";
        try {
            URL u = new URL(url);
            String dataFile = "redblueball.txt";
            BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile)));

            String temp;
            while ((temp = reader.readLine()) != null) {
                // split by empty
                String[] splits = temp.split(" ", 8);
                if (splits.length != 8) {
                    continue;
                }

                writer.write(splits[0].trim());

                RedBall[] rbs = new RedBall[6];
                for (int i = 1; i < 7; i++) {
                    writer.write(" ");

                    int number = Integer.parseInt(splits[i]);
                    rbs[i - 1] = RedBall.valueOf(number);
                    writer.write(rbs[i - 1].toString());
                }

                writer.write(" +");
                int number = Integer.parseInt(splits[7]);
                Ball bb = BlueBall.valueOf(number);
                writer.write(bb.toString());

                writer.newLine();
            }

            System.out.println("update sucess");

            reader.close();
            writer.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
