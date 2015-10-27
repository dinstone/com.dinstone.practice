
package com.dinstone.practice.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeMain {

    public static void main(String[] args) throws ClassNotFoundException {

        Child one = new Child("david");

        FileOutputStream fos;
        FileInputStream fis;
        try {
//            fos = new FileOutputStream("child.tmp");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(one);
//            oos.close();

            fis = new FileInputStream("child.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            System.out.println(obj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
