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

package com.dinstone.practice.classloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @author guojf
 * @version 1.0.0.2012-2-10
 */
public class ClassloaderUtil {

    /**
     * 在类路径下查找文件.
     * 
     * @param location
     *        基于类路径下的文件
     * @return
     * @throws Exception
     */
    public static File findClassPathFile(String location) throws Exception {
        URL resource = getClassLoader().getResource(location);
        if (resource == null) {
            throw new FileNotFoundException("类路径下找不到文件:" + location);
        }

        return new File(resource.toURI());
    }

    /**
     * Description: 取得类加载器
     * 
     * @return
     */
    private static ClassLoader getClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = ClassloaderUtil.class.getClassLoader();
        }
        return cl;
    }

    public static void main(String[] args) {
        try {
            File pf = ClassloaderUtil.findClassPathFile("sms/phones-1.txt");
            System.out.println(pf);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
