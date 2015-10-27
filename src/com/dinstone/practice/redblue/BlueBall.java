
package com.dinstone.practice.redblue;

/**
 * @author guojf
 * @version 1.0.0.2013-1-1
 */
public class BlueBall extends Ball {

    private static BlueBall[] balls = new BlueBall[16];

    static {
        for (int i = 0; i < 16; i++) {
            balls[i] = new BlueBall(i + 1);
        }
    }

    private BlueBall(int number) {
        super(number);
    }

    private static BlueBall[] values() {
        return balls;
    }

    /**
     * @param number
     * @return
     */
    public static BlueBall valueOf(int number) {
        for (BlueBall ball : BlueBall.values()) {
            if (ball.getNumber() == number) {
                return ball;
            }
        }

        return null;
    }

}
