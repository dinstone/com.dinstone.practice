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

package com.dinstone.practice.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.dinstone.practice.classloader.ClassloaderUtil;

/**
 * @author guojf
 * @version 1.0.0.2012-3-30
 */
public class Utf8Test {

    public static void main(String[] args) throws Exception {
        File f = ClassloaderUtil.findClassPathFile("utf8-bom.txt");
        FileInputStream in = new FileInputStream(f);
        // 指定读取文件时以UTF-8的格式读取
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        byte[] b = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
        System.out.println(new String(b, "utf-8"));

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);

            line = removeBom(line);

            byte[] allbytes = line.getBytes("UTF-8");
            for (int i = 0; i < allbytes.length; i++) {
                int tmp = allbytes[i];
                String hexString = Integer.toHexString(tmp);
                // 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充
                hexString = hexString.substring(hexString.length() - 2);
                System.out.print(hexString.toUpperCase());
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    /**
     * @param line
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String removeBom(String line) throws UnsupportedEncodingException {
        byte[] allbytes = line.getBytes("UTF-8");
        if (allbytes.length > 2 && (allbytes[0] == (byte) 0xEF) && (allbytes[1] == (byte) 0xBB)
                && (allbytes[2] == (byte) 0xBF)) {
            return new String(allbytes, 3, allbytes.length - 3, "utf-8");
        }

        return line;
    }
}
