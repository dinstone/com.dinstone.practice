
package com.dinstone.practice.jsa.cipher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

/**
 * 公钥密码术加密:<br>
 * 公钥加密比较慢（比私钥加密慢 100 到 1,000 倍），因此实际上通常使用混合技术。<br>
 * 公钥加密被用于向对方分发称为会话密钥的私钥，使用该私有会话密钥的私钥加密被用于进行大量的消息加密。
 * 
 * @author guojf
 * @version 1.0.0.2014-3-10
 */
public class PublicExample {

    public static void main(String[] args) throws Exception {
        //
        // check args and get plaintext
        if (args.length != 1) {
            System.err.println("Usage: java PublicExample text");
            System.exit(1);
        }
        byte[] plainText = args[0].getBytes("UTF8");
        //
        // generate an RSA key
        System.out.println("\nStart generating RSA key");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        System.out.println("Finish generating RSA key");
        //
        // get an RSA cipher object and print the provider
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        System.out.println("\n" + cipher.getProvider().getInfo());
        //
        // encrypt the plaintext using the public key
        System.out.println("\nStart encryption");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println("Finish encryption: ");
        System.out.println(new String(cipherText, "UTF8"));
        //
        // decrypt the ciphertext using the private key
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println("Finish decryption: ");
        System.out.println(new String(newPlainText, "UTF8"));
    }
}
