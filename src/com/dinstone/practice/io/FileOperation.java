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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOperation {

    /**
     * Description:
     * 
     * @param args
     */
    public static void main(String[] args) throws URISyntaxException {

        checkFile();
        try {
            createFolder("file");
            createFolder("file/中文  目录");
            createFile("file/opt/fo.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        url2file();
    }

    /**
     * Description:创建文件夹，若文件不存在
     * 
     * @param string
     * @return
     * @throws IOException
     */
    private static File createFolder(String folderName) throws IOException {
        File path = new File(folderName);
        File file = path.getCanonicalFile();
        System.out.println("创建文件夹：" + file.getPath());
        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }

    /**
     * Description: 创建文件，若文件不存在
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    private static File createFile(String fileName) throws IOException {
        File path = new File(fileName);
        File file = path.getCanonicalFile();
        System.out.println("创建文件：" + file.getPath());
        if (!file.exists()) {
            File pf = file.getParentFile();
            pf.mkdirs();
            file.createNewFile();
        }

        return file;
    }

    private static void checkFile() {
        System.out.println("用户当前工作目录是" + System.getProperty("user.dir"));
        System.out.println("=================================================");

        // 构造当前相对路径，默认是user.dir
        checkPathName(".");
        System.out.println("=================================================");

        // 构造当前相对路径的父路径
        checkPathName("../.");
        System.out.println("=================================================");

        // 构造当前相对路径，默认是user.dir
        checkPathName("");
        System.out.println("=================================================");

        // 构造绝对路径，默认是根路径
        checkPathName("/");
        System.out.println("=================================================");

        // 构造绝对路径
        checkPathName("/D://aaa//up1311577994177.zip");
        System.out.println("=================================================");

        // 构造绝对路径
        checkPathName("/aaa/bbb");
        System.out.println("=================================================");
    }

    private static void checkPathName(String path) {
        System.out.println("要检查的文件参数是" + path);
        // 原始文件
        File file = new File(path);
        checkFileAttribute(file);
        System.out.println("*************************************************");
        try {
            // 规范文件
            file = file.getCanonicalFile();
            checkFileAttribute(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description:测试文件属性
     * 
     * @param file
     */
    private static void checkFileAttribute(File file) {
        System.out.println("文件名是" + file.getName());
        System.out.println("文件路径是" + file.getPath());
        System.out.println("文件绝对路径是" + file.getAbsolutePath());
        try {
            System.out.println("文件规范路径是" + file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 测试文件是否存在
        if (file.exists()) {
            System.out.println("文件存在");

            // 测试文件是否可读
            if (file.canRead()) {
                System.out.println("文件可读");
            }

            // 测试文件是否可写
            if (file.canWrite()) {
                System.out.println("文件可写");
            }

            // 测试文件是否绝对路径
            if (file.isAbsolute()) {
                System.out.println("文件为绝对路径");
            } else {
                System.out.println("文件为相对路径");
            }

            String ds = getLastModifyDate(file);
            // 文件的最后修改时间
            System.out.println("文件的最后修改时间是 " + ds);

            // 测试文件是否目录
            if (file.isDirectory()) {
                System.out.println("文件为目录类型");
            } else if (file.isFile()) {
                System.out.println("文件为文件类型");
                System.out.println("文件大小是" + file.length());
            } else {
                System.out.println("文件为未知类型");
            }

            // 测试是否隐藏文件
            if (file.isHidden()) {
                System.out.println("文件为隐藏文件");
            }
        } else {
            System.out.println("文件不存在");
        }
    }

    private static String getLastModifyDate(File file) {
        Date lmd = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ds = sdf.format(lmd);
        return ds;
    }

    private static void url2file() throws URISyntaxException {
        File file = new File("C:/Documents and Settings/guojf/Local Settings/Temp/.");
        try {
            URL furl = file.toURI().toURL();
            System.out.println("将toURI到toURL编码后的文件名：" + furl.getFile());
            File newFile = new File(furl.getFile());
            if (newFile.exists()) {
                System.out.println("将toURI到toURL编码后的文件找到了");
            } else {
                System.out.println("将toURI到toURL编码后的文件找不到");
            }

            System.out.println("将toURL编码后的文件名：" + file.toURL().getFile());
            newFile = new File(file.toURL().getFile());
            if (newFile.exists()) {
                System.out.println("将toURL编码后的文件找到了");
            } else {
                System.out.println("将toURL编码后的文件找不到");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
