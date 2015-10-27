
package com.dinstone.practice.redblue;

public class SampleGen {

    /**
     * @param args
     */
    public static void main(String[] args) {
        c33s6();

//        int m = 33;
//        int n = 6;
//        cmsn(m, n);
    }

    private static void cmsn(int m, int n) {
        if (m < n) {
            return;
        }

        for (int i = 1; i < m - n + 2; i++) {

        }
    }

    private static void c33s6() {
        int[] rs = new int[7];

        for (int i = 1; i < 29; i++) {
            rs[1] = i;
            for (int j = i + 1; j < 30; j++) {
                rs[2] = j;
                for (int k = j + 1; k < 31; k++) {
                    rs[3] = k;
                    for (int r = k + 1; r < 32; r++) {
                        rs[4] = r;
                        for (int s = r + 1; s < 33; s++) {
                            rs[5] = s;
                            for (int t = s + 1; t < 34; t++) {
                                rs[6] = t;
                                rs[0]++;
                                System.out.println(rs[0] + " : " + rs[1] + " " + rs[2] + " " + rs[3] + " " + rs[4]
                                        + " " + rs[5] + " " + rs[6]);
                            }
                        }
                    }
                }
            }
        }
    }
}
