/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter18.recipe18_03;

import java.util.concurrent.Future;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTaskListener;

/**
 *
 * @author Juneau
 */
public class CustomManagedTaskListener implements ManagedTaskListener {

    @Override
    public void taskSubmitted(Future<?> future, ManagedExecutorService mes, Object o) {
        System.out.println("Task Submitted");
    }

    @Override
    public void taskAborted(Future<?> future, ManagedExecutorService mes, Object o, Throwable thrwbl) {
        System.out.println("Task Aborted");
    }

    @Override
    public void taskDone(Future<?> future, ManagedExecutorService mes, Object o, Throwable thrwbl) {
        System.out.println("Task Complete");
    }

    @Override
    public void taskStarting(Future<?> future, ManagedExecutorService mes, Object o) {
        System.out.println("Task Starting");
    }
    
}
