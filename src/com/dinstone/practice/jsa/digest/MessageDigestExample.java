
package com.dinstone.practice.jsa.digest;

import java.security.MessageDigest;

/**
 * 消息摘要关注消息的完整性。公开性消息摘要实现
 * 
 * @author guojf
 * @version 1.0.0.2014-3-10
 */
public class MessageDigestExample {

    public static void main(String[] args) throws Exception {
        // check args and get plaintext
        if (args.length != 1) {
            System.err.println("Usage: java MessageDigestExample text");
            System.exit(1);
        }

        byte[] plainText = args[0].getBytes("UTF8");

        // get a message digest object using the MD5 algorithm
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //
        // print out the provider used
        System.out.println("\n" + messageDigest.getProvider().getInfo());
        //
        // calculate the digest and print it out
        messageDigest.update(plainText);
        System.out.println("\nDigest: ");
        System.out.println(new String(messageDigest.digest(), "UTF8"));
    }
}
