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

package com.dinstone.practice.jndi;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DnsJndiLookup {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            String domain = "sina.com";
            String dnsServer = "//10.1.1.7";

            Properties prop = new Properties();
            // set content initial factory,access DNS naming service
            prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            // set provider url
            prop.put(Context.PROVIDER_URL, "dns:" + dnsServer);

            DirContext ctx = new InitialDirContext(prop);
            // 分别获取包含所有属性和只包含Mx属性的Attributes对象
            Attributes attrsAll = ctx.getAttributes(domain);
            Attributes attrsMx = ctx.getAttributes(domain, new String[] { "MX" });

            /*
             * 上面的整段程序代码也可以用下面这段程序代码来替代，下面这段程序
             * 代码通过查询URL中的Schema信息来自动选择Context的工厂类
             */
            /*
             * DirContext ctx = new InitialDirContext(); Attributes attrsAll =
             * ctx.getAttributes("dns:" + dnsServer + "/" + domain); Attributes
             * attrsMx = ctx.getAttributes( "dns:" + dnsServer + "/" + domain,
             * new String[]{"MX"});
             */

            System.out.println("打印出域" + domain + "的Attributes对象中的信息：");
            System.out.println(attrsAll);
            System.out.println("--------------------------");
            System.out.println("打印只检索域" + domain + "的MX记录的Attributes对象：");
            System.out.println(attrsMx);

            System.out.println("--------------------------");
            System.out.println("逐一打印出Attributes对象中的各个属性：");
            NamingEnumeration attributes = attrsAll.getAll();
            while (attributes.hasMore()) {
                System.out.println(attributes.next());
            }

            System.out.println("--------------------------");
            // 直接调用get方法从attrsMx集合检索MX属性
            System.out.println("直接检索Attributes对象中的MX属性：");
            Attribute attrMx = attrsAll.get("MX");
            System.out.println(attrMx);

            System.out.println("--------------------------");
            // 获取Mx属性中的第一个值:
            System.out.println("获取Mx属性中的第一个值:");
            String recordMx = (String) attrMx.get();
            System.out.println(recordMx);
            // 从Mx属性的第一个值中提取邮件服务器地址
            System.out.println("从MX属性值中提取的邮件服务器地址：");
            String smtpServer = recordMx.substring(recordMx.indexOf(" ") + 1);
            System.out.println(smtpServer);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

}
