
package com.dinstone.practice.redblue;

import java.text.NumberFormat;

public class Ball {

    protected int number;

    public Ball(int number) {
        this.number = number;
    }

    /**
     * the number to get
     * 
     * @return the number
     * @see BlueBall#number
     */
    public int getNumber() {
        return number;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(2);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(2);

        return nf.format(number);
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
        result = prime * result + number;
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
        Ball other = (Ball) obj;
        if (number != other.number) {
            return false;
        }
        return true;
    }

}
