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

package com.dinstone.practice.exception;

public class FinallyHandle {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("reture value of test1() : " + test1());
        System.out.println("======================================");
        System.out.println("reture value of test2() : " + test2());
        System.out.println("======================================");
        System.out.println("reture value of test3() : " + test3());
        System.out.println("======================================");
        System.out.println("reture value of test4() : " + test4());
        System.out.println("======================================");
        System.out.println("reture value of test5() : " + test5());
    }

    /**
     * finally 语句块是在 try 或者 catch 中的 return 语句之前执行的
     * 
     * @return 1
     */
    public static int test1() {
        int i = 1;
        try {
            System.out.println("try block");
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception block");
            return 2;
        } finally {
            System.out.println("finally block");
        }
    }

    /**
     * finally 语句块是在 try 或者 catch 中的 return 语句之前执行的
     * 
     * @return 2
     */
    public static int test2() {
        int i = 1;
        try {
            System.out.println("try block");
            i = 1 / 0;
            return i;
        } catch (Exception e) {
            System.out.println("exception block");
            return 2;
        } finally {
            System.out.println("finally block");
        }
    }

    /**
     * 隐藏了异常信息
     * 
     * @return 2
     */
    @SuppressWarnings("finally")
    public static int test3() {
        int i = 1;
        try {
            System.out.println("try block");
            i = 1 / 0;
            return i;
        } catch (Exception e) {
            System.out.println("exception block");
            i++;
            throw new RuntimeException("warpper exception", e);
        } finally {
            System.out.println("finally block");
            return i;
        }
    }

    /**
     * Java 虚拟机会把 finally 语句块作为 subroutine直接插入到 try 语句块或者 catch 语句块的控制转移语句之前,在执行
     * subroutine（也就是 finally 语句块）之前,try 或者 catch 语句块会保留其返回值到本地变量表（Local
     * Variable Table）中,保留返回值只适用于 return 和 throw 语句, 不适用于 break 和 continue
     * 语句,因为它们根本就没有返回值。
     * 
     * @return 1
     */
    public static int test4() {
        int i = 1;
        try {
            System.out.println("try block");
            return i;
        } finally {
            System.out.println("finally block");
            i++;
        }
    }

    /**
     * finally语句异常
     * 
     * @return
     */
    public static int test5() {
        int i = 1;
        try {
            System.out.println("try block");
            return i;
        } finally {
            System.out.println("finally block");
            i = i / 0;
        }
    }

}
