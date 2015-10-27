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

package com.dinstone.practice.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author guojf
 * @version 1.0.0.2012-2-8
 */
public class CharsetTest {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        byte[] cb = hexStringToBytes("C0220CF05FCF40107CCF4010C68B10F0F8220CF0B88B10F0EB8042102");
        String sc = new String(cb, "utf-8");
        System.out.println("[" + sc + "]");

        // charTest();
    }

    private static void charTest() {
        try {
            String cs = "a郭";
            System.out.println("default = " + cs.getBytes().length);
            System.out.println("utf-8 = " + cs.getBytes("UTF-8").length);
            System.out.println("gbk = " + cs.getBytes("GBK").length);

            // “汉”的UnicodeBigUnmarked编码
            byte[] cb = new byte[] { 0x6c, 0x49 };
            String sc = new String(cb, "UnicodeBigUnmarked");
            System.out.println("[" + sc + "]");

            // “汉”的GBK编码
            cb = new byte[] { (byte) 0xBA, (byte) 0xBA };
            sc = new String(cb, "GBK");
            System.out.println("[" + sc + "]");

            // “中”的Unicode编码
            String zw = "\u4E2D";
            System.out.println(zw);

            // “汉”的UnicodeBigUnmarked编码
            zw = "\u6c49";
            System.out.println(zw);

            // '['的UnicodeBigUnmarked编码
            zw = "\u005b";
            System.out.println(zw);

            // ']'的UnicodeBigUnmarked编码
            zw = "\u005d";
            System.out.println(zw);

            String scp = "newStatus=0&updateEnames%5B%5D=NotPrivilege&updateEnames%5B%5D=SemanticNewTwo&updateEnames%5B%5D=%E6%B1%89";
            String pa = URLDecoder.decode(scp, "utf-8");
            System.out.println("pa = " + pa);

            String ps = URLEncoder.encode("汉", "utf-8");
            System.out.println("ps = " + ps);

            cb = new byte[] { (byte) 0xE6, (byte) 0xB1, (byte) 0x89 };
            String sf = new String(cb, "utf-8");
            System.out.println("[" + sf + "]");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert hex string to byte[]
     * 
     * @param hexString
     *        the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
