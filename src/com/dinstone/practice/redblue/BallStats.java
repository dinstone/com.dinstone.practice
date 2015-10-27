
package com.dinstone.practice.redblue;

import java.util.Comparator;

public class BallStats implements Comparable<BallStats> {

    public static class Comp implements Comparator<BallStats> {

        public int compare(BallStats o1, BallStats o2) {
            return o1.getBall().getNumber() - o2.getBall().getNumber();
        }

    }

    private Ball ball;

    private int count;

    public BallStats(Ball ball) {
        this.ball = ball;
    }

    public BallStats(Ball ball, int count) {
        super();
        this.ball = ball;
        this.count = count;
    }

    /**
     * the ball to get
     * 
     * @return the ball
     * @see BallStats#ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * the count to get
     * 
     * @return the count
     * @see BallStats#count
     */
    public int getCount() {
        return count;
    }

    /**
     * the count to set
     * 
     * @param count
     * @see BallStats#count
     */
    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(BallStats o) {
        return o.count - this.count;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ball + "=" + count;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ball == null) ? 0 : ball.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BallStats other = (BallStats) obj;
        if (ball == null) {
            if (other.ball != null) {
                return false;
            }
        } else if (!ball.equals(other.ball)) {
            return false;
        }
        return true;
    }

}
