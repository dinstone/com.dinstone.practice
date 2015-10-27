
package com.dinstone.practice.redblue;

import java.util.Arrays;

public class WinningInfo {

    /** 期号 */
    private String issue;

    private Ball[] redBalls;

    private Ball blueBall;

    public WinningInfo(String issue, RedBall[] redBalls, Ball blueBall) {
        super();
        this.issue = issue;
        this.redBalls = redBalls;
        this.blueBall = blueBall;
    }

    /**
     * the issue to get
     * 
     * @return the issue
     * @see WinningInfo#issue
     */
    public String getIssue() {
        return issue;
    }

    /**
     * the redBalls to get
     * 
     * @return the redBalls
     * @see WinningInfo#redBalls
     */
    public Ball[] getRedBalls() {
        return redBalls;
    }

    /**
     * the blueBall to get
     * 
     * @return the blueBall
     * @see WinningInfo#blueBall
     */
    public Ball getBlueBall() {
        return blueBall;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{issue=" + issue + ", redBalls=" + Arrays.toString(redBalls) + ", blueBall=" + blueBall + "}";
    }

}
