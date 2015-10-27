
package com.dinstone.practice.io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * java 中控制台输入的几种实现方法
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-10-21 下午4:55:30
 * @version 1.0
 */
public class ConsoleInput {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // jdk1.5 之前的实现方式
        // test1();

        // jdk5.0的Scanner实现方式
        test2();

        // jdk6.0中Console实现方式 在IDE中获取console会失败
        // test3();
    }

    // jdk1.5 之前的实现方式
    public static void test1() throws Exception {
        String line = null;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("please input :> ");
        while (!"exit".equals(line = buffer.readLine())) {
            System.out.println("input context = " + line);
            System.out.print("please input :> ");
        }
        System.out.println("The program will exit");
    }

    // jdk5.0 增加了java.util.Scanner类
    public static void test2() {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        System.out.print("please input :> ");
        while (!"exit".equals(line = scanner.nextLine())) {
            System.out.println("input context = " + line);
            System.out.print("please input :> ");
        }
        System.out.println("The program will exit");
    }

    // jdk6.0 后增加java.io.Console(但在IDE中获取console会失败)
    public static void test3() {
        Console console = System.console();
        if (null == console) {
            System.out.println("Console is unavailable");
            System.exit(0);
        }
        String line = null;
        System.out.println(console);
        while (!"exit".equals(line = console.readLine("please input keyword :> "))) {
            System.out.println("input context = " + line);
        }
        System.out.println("The program will exit");
    }

}