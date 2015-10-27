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
import java.nio.charset.Charset;
import java.util.Set;

public class CharsetEncode {

    private final static char[] HEX = "0123456789abcdef".toCharArray();

    /**
     * Description:
     * 
     * @param args
     */
    public static void main(String[] args) {
        defaultCharset();
        availableCharsets();
        checkSupported("Unicode");
        try {
            charsetEncoding();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符集编码
     * 
     * @throws UnsupportedEncodingException
     */
    private static void charsetEncoding() throws UnsupportedEncodingException {
        String str = "I am 中国人[]";
        String[] encoding = { "Unicode", "UnicodeBig", "UnicodeLittle", "UnicodeBigUnmarked", "UnicodeLittleUnmarked",
                "UTF-16", "UTF-16BE", "UTF-16LE", "UTF-8", "GB2312", "GBK", "GB18030" };

        for (int i = 0; i < encoding.length; i++) {
            System.out.printf("%-22s %s%n", encoding[i], bytes2HexString(str.getBytes(encoding[i])));
        }
    }

    public static String bytes2HexString(byte[] bys) {
        char[] chs = new char[bys.length * 2 + bys.length - 1];
        for (int i = 0, offset = 0; i < bys.length; i++) {
            if (i > 0) {
                chs[offset++] = ' ';
            }
            // 右移4位，然后取与
            chs[offset++] = HEX[bys[i] >> 4 & 0xf];
            chs[offset++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }

    /**
     * 测试某些字符集是否支持
     */
    private static void checkSupported(String charsetName) {
        if (Charset.isSupported(charsetName)) {
            System.out.println("系统当前支持字符集\"" + charsetName + "\"");
        } else {
            System.out.println("系统当前不支持字符集\"" + charsetName + "\"");
        }
    }

    /**
     * 测试当前环境的默认字符集
     */
    private static void defaultCharset() {
        // file.encoding 系统变量指定
        System.out.println("当前环境的默认字符集: " + Charset.defaultCharset());
    }

    /**
     * 测试系统支持的字符集
     */
    private static void availableCharsets() {
        Set<String> charsets = Charset.availableCharsets().keySet();
        System.out.println("系统支持的字符集如下：");
        for (String cs : charsets) {
            System.out.println(cs);
        }
    }

}
