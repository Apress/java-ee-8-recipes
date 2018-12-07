
package org.javaee8recipes.chapter13.recipe13_02;

import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

/**
 *
 * @author juneau
 */
@Named("myBean")
@RequestScoped
public class CalculationBean implements java.io.Serializable{
    
    private int num1 = 1;
    private int num2 = 0;
    private int sum;
    
    public CalculationBean(){
    }
    
    
    public void addNumbers(){
        System.out.println("Called");
        setSum(getNum1() + getNum2());
    }

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
        System.out.println("setting num1");
        this.num1 = num1;
    }

    /**
     * @return the num2
     */
    public int getNum2() {
        return num2;
    }

    /**
     * @param num2 the num2 to set
     */
    public void setNum2(int num2) {
        this.num2 = num2;
    }

    /**
     * @return the sum
     */
    public int getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(int sum) {
        this.sum = sum;
    }
    
}
