
package com.dinstone.practice.jsa.signature;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;

import javax.crypto.Cipher;

/**
 * 数字签名用来解决证实消息来自特定通信方的问题。<br>
 * 使用消息摘要和公钥密码术的原语来实现数字签名
 * 
 * @author guojf
 * @version 1.0.0.2014-3-10
 */
public class DigitalSignature1Example {

    public static void main(String[] args) throws Exception {
        //
        // check args and get plaintext
        if (args.length != 1) {
            System.err.println("Usage: java DigitalSignature1Example text");
            System.exit(1);
        }
        byte[] plainText = args[0].getBytes("UTF8");
        //
        // get an MD5 message digest object and compute the plaintext digest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        System.out.println("\n" + messageDigest.getProvider().getInfo());
        messageDigest.update(plainText);
        byte[] md = messageDigest.digest();
        System.out.println("\nDigest: ");
        System.out.println(new String(md, "UTF8"));
        //
        // generate an RSA keypair
        System.out.println("\nStart generating RSA key");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        System.out.println("Finish generating RSA key");
        //
        // get an RSA cipher and list the provider
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        System.out.println("\n" + cipher.getProvider().getInfo());
        //
        // encrypt the message digest with the RSA private key
        // to create the signature
        System.out.println("\nStart encryption");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
        byte[] cipherText = cipher.doFinal(md);
        System.out.println("Finish encryption: ");
        System.out.println(new String(cipherText, "UTF8"));
        //
        // to verify, start by decrypting the signature with the
        // RSA private key
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key.getPublic());

        byte[] newMD = cipher.doFinal(cipherText);
        System.out.println("Finish decryption: ");
        System.out.println(new String(newMD, "UTF8"));
        //
        // then, recreate the message digest from the plaintext
        // to simulate what a recipient must do
        System.out.println("\nStart signature verification");
        messageDigest.reset();
        messageDigest.update(plainText);
        byte[] oldMD = messageDigest.digest();
        //
        // verify that the two message digests match
        int len = newMD.length;
        if (len > oldMD.length) {
            System.out.println("Signature failed, length error");
            System.exit(1);
        }
        for (int i = 0; i < len; ++i)
            if (oldMD[i] != newMD[i]) {
                System.out.println("Signature failed, element error");
                System.exit(1);
            }
        System.out.println("Signature verified");
    }
}
