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

package com.dinstone.practice.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.junit.Test;

public class SerializeTest {

    @Test
    public void testSerialize001() {
        ObjA a = new ObjA("guojinfei", 28);

        FileOutputStream fos;
        FileInputStream fis;
        try {
            fos = new FileOutputStream("s.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(12345);
            oos.writeObject("Today");
            oos.writeObject(new Date());
            oos.writeObject(a);

            oos.close();

            fis = new FileInputStream("s.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(ois.readInt());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            Object obj = ois.readObject();
            System.out.println(obj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
