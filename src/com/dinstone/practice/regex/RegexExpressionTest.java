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

package com.dinstone.practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class RegexExpressionTest {

    /**
     * Description:贪婪匹配(Greedy)，尽可能多的匹配字符.
     * <ul>
     * <li>Regex: ".*bbb"</li>
     * <li>Input: "abbbaabbbaaabbb1234"</li>
     * </ul>
     * 
     * <pre>
     * a)abbbaabbbaaabbb1234
     * b)abbbaabbbaaabbb123
     * c)abbbaabbbaaabbb12
     * d)abbbaabbbaaabbb1
     * e)abbbaabbbaaabbb
     * </pre>
     */
    @Test
    public void testRegex001() {
        String input = "abbbaabbbaaabbb1234";
        String regex = ".*bbb";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        Assert.assertEquals(true, m.find());
        Assert.assertEquals(0, m.groupCount());
        Assert.assertEquals("abbbaabbbaaabbb", m.group());
    }

    /**
     * Description:惰性匹配(Reluctant)，尽可能少的匹配字符.
     * <ul>
     * <li>Regex: ".*?bbb"</li>
     * <li>Input: "abbbaabbbaaabbb1234"</li>
     * </ul>
     * 
     * <pre>
     * a)a
     * b)ab
     * c)abb
     * d)abbb //保存结果，并从下一个位置重新开始
     * 
     * e)a
     * f)aa
     * g)aab
     * h)aabb
     * j)aabbb //保存结果，并从下一个位置重新开始
     * 
     * h)a
     * i)aa
     * j)aaa
     * k)aaab
     * l)aaabb
     * m)aaabbb //保存结果，并从下一个位置重新开始
     * </pre>
     */
    @Test
    public void testRegex002() {
        String input = "abbbaabbbaaabbb1234";
        String regex = ".*?bbb";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        Assert.assertEquals(true, m.find());
        Assert.assertEquals(0, m.groupCount());
        Assert.assertEquals("abbb", m.group());

        Assert.assertEquals(true, m.find());
        Assert.assertEquals(0, m.groupCount());
        Assert.assertEquals("aabbb", m.group());

        Assert.assertEquals(true, m.find());
        Assert.assertEquals(0, m.groupCount());
        Assert.assertEquals("aaabbb", m.group());
    }

    /**
     * Description:支配匹配(Possessive)，只尝试匹配整个字符串。 <br/>
     * 如果整个字符串不能产生匹配，不做进一步尝试.
     * <ul>
     * <li>Regex: ".*+1234"</li>
     * <li>Input: "abbbaabbbaaabbb1234"</li>
     * </ul>
     */
    @Test
    public void testRegex003() {
        String input = "abbbaabbbaaabbb1234";
        String regex = ".*+1234";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        Assert.assertEquals(false, m.find());
    }

    /**
     * Description:贪婪匹配(Greedy)，尽可能多的匹配字符.
     * <ul>
     * <li>Regex: "a.*1234"</li>
     * <li>Input: "sdabbbaabbbaaabbb1234"</li>
     * </ul>
     * 
     * <pre>
     * a)abbbaabbbaaabbb1234
     * </pre>
     */
    @Test
    public void testRegex004() {
        String input = "sdabbbaabbbaaabbb1234";
        String regex = "a.*1234";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        Assert.assertEquals(true, m.find());
        Assert.assertEquals("abbbaabbbaaabbb1234", m.group());
    }

    @Test
    public void testRegex005() {
        String input = "11:59am";
        String regex = "((1?[0-9]):([0-5][0-9]))[ap]m";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        Assert.assertEquals(true, m.find());
        Assert.assertEquals("11:59am", m.group());

        Assert.assertEquals("11:59", m.group(1));
        Assert.assertEquals("11", m.group(2));
        Assert.assertEquals("59", m.group(3));
    }

    @Test
    public void testRegex006() {
        String input = "11:59am";
        String regex = "([0-9]+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        // System.out.println(m.group(0));
        String r = m.replaceAll("#");

        Assert.assertEquals("#:#am", r);
    }

    @Test
    public void testRegex007() {
        String input = "<table><td>sdjfjfiweif</td></table><cd>sdfsdf</cd>";
        String regex = "<(\\w+)>(.*)</(\\1)>";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
        }

    }

}
