
package com.dinstone.practice.redblue;

/**
 * @author guojf
 * @version 1.0.0.2013-1-1
 */
public class RedBall extends Ball {

    private static RedBall[] balls = new RedBall[33];

    static {
        for (int i = 0; i < 33; i++) {
            balls[i] = new RedBall(i + 1);
        }
    }

    private RedBall(int number) {
        super(number);
    }

    /**
     * @param number
     * @return
     */
    public static RedBall valueOf(int number) {
        for (RedBall ball : RedBall.values()) {
            if (ball.getNumber() == number) {
                return ball;
            }
        }

        return null;
    }

    private static RedBall[] values() {
        return balls;
    }

}
