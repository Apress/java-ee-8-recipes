
package org.javaee8recipes.chapter02.recipe02_07;

/**
 * Recipe 2-7
 * @author juneau
 */
public class Expressions implements java.io.Serializable {
    private int num1 = 5;
    private double num2 = 634.324;
    private float num3 = 98.4f;

    /**
     * @return the num1
     */
    public int getNum1() {
        return num1;
    }

    /**
     * @param num1 the num1 to set
     */
    public void setNum1(int num1) {
        this.num1 = num1;
    }

    /**
     * @return the num2
     */
    public double getNum2() {
        return num2;
    }

    /**
     * @param num2 the num2 to set
     */
    public void setNum2(double num2) {
        this.num2 = num2;
    }

    /**
     * @return the num3
     */
    public float getNum3() {
        return num3;
    }

    /**
     * @param num3 the num3 to set
     */
    public void setNum3(float num3) {
        this.num3 = num3;
    }
}
