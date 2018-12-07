
package org.javaee8recipes.chapter13;

import javax.inject.Inject;

/**
 *
 * @author juneau
 */
public class UsingClass {
    
    @Inject
    CalculationBean calcBean;
    
    public void performCalculation(){
        int[] intarr = new int[2];
        intarr[0] = 2;
        intarr[1] = 3;
        System.out.println("The sum of 2 + 3:" + calcBean.addNumbers(intarr));
    }
    
}
