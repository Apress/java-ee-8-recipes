
package org.javaee8recipes.chapter13;

import javax.inject.Named;


/**
 *
 * @author juneau
 */
@Named
public class CalculationBean {
    
    public int addNumbers(int[] numArray){
        int temp = 0;
        for(int x : numArray){
            temp = temp + x;
        }
        return temp;
    }
    
}
