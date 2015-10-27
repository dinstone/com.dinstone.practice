
package com.dinstone.practice.format;

import java.text.NumberFormat;

public class NumFormatTest {

    public static void main(String[] args) {
        int n = 1;

        NumberFormat nf = NumberFormat.getNumberInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(2);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(2);

        String ns = nf.format(n);
        System.out.println(ns);

        ns = nf.format(2);
        System.out.println(ns);
    }
}
