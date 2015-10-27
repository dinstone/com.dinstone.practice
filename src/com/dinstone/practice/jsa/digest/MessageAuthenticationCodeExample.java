
package com.dinstone.practice.jsa.digest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/**
 * 消息摘要关注消息的完整性。消息认证码是私密性消息摘要实现.
 * 
 * @author guojf
 * @version 1.0.0.2014-3-10
 */
public class MessageAuthenticationCodeExample {

    public static void main(String[] args) throws Exception {
        //
        // check args and get plaintext
        if (args.length != 1) {
            System.err.println("Usage: java MessageAuthenticationCodeExample text");
            System.exit(1);
        }
        byte[] plainText = args[0].getBytes("UTF8");
        //
        // get a key for the HmacMD5 algorithm
        System.out.println("\nStart generating key");
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey md5key = keyGen.generateKey();
        System.out.println("Finish generating key :" + md5key.getAlgorithm());
        //
        // get a MAC object and update it with the plaintext
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(md5key);
        mac.update(plainText);
        //
        // print out the provider used and the MAC
        System.out.println("\n" + mac.getProvider().getInfo());
        System.out.println("\nMAC: ");
        System.out.println(new String(mac.doFinal(), "iso-8859-1"));

    }
}
