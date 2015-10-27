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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class RunOnHadoop {

    public File createTempJar(String root) throws IOException {
        File file = new File(root);
        if (!file.exists()) {
            return null;
        }
        root = file.getAbsolutePath();

        Manifest manifest = new Manifest();
        manifest.getMainAttributes().putValue("Manifest-Version", "1.0");
        manifest.getMainAttributes().putValue("Build-Jdk", "1.6.0_22");
        File jarFile = File.createTempFile("EJob-", ".jar", new File(System.getProperty("java.io.tmpdir")));
        JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(jarFile), manifest);
        createJarFile(jarOut, file, "");
        jarOut.flush();
        jarOut.close();

        return jarFile;

    }

    private void createJarFile(JarOutputStream out, File f, String base) throws IOException {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (base.length() > 0) {
                base = base + "/";
            }
            for (File file : fl) {
                createJarFile(out, file, base + file.getName());
            }
        } else {
            out.putNextEntry(new JarEntry(base));
            FileInputStream in = new FileInputStream(f);

            byte[] b = new byte[1024];
            int n = 0;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
        }
    }

    public static void main(String[] args) {
        RunOnHadoop ro = new RunOnHadoop();
        File jf;
        try {
            jf = ro.createTempJar("bin");
            System.out.println(jf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
