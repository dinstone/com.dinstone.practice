
package com.dinstone.practice.jsa.cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 * 私钥密码术加密:<br>
 * 私钥的分发一般借助于公钥密码术传送.
 * 
 * @author guojf
 * @version 1.0.0.2014-3-10
 */
public class PrivateExample {

    public static void main(String[] args) throws Exception {
        //
        // check args and get plaintext
        // if (args.length != 1) {
        // System.err.println("Usage: java PrivateExample text");
        // System.exit(1);
        // }
        // aes(args[0]);

        String keyChars = "BFB673CB9B249023";
        // keyChars.getBytes("utf-8");
        byte[] kbs = parseHexStr2Byte(keyChars);
        System.out.println("len = " + kbs.length);

        System.out.println("Start generating AES key");
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();
        System.out.println("Finish generating AES key");

        System.out.println(new String(key.getEncoded(), "utf-8"));

    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static void aes(String text) throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] plainText = text.getBytes("UTF8");
        //
        // get a DES private key
        System.out.println("\nStart generating DES key");
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        Key key = keyGen.generateKey();
        System.out.println("Finish generating DES key");
        //
        // get a DES cipher object and print the provider
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        System.out.println("\n" + cipher.getProvider().getInfo());
        //
        // encrypt using the key and the plaintext
        System.out.println("\nStart encryption");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println("Finish encryption: ");
        System.out.println(new String(cipherText, "UTF8"));

        //
        // decrypt the ciphertext using the same key
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println("Finish decryption: ");

        System.out.println(new String(newPlainText, "UTF8"));
    }
}
