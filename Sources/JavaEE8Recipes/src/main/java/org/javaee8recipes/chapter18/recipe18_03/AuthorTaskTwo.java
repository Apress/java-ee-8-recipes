
package org.javaee8recipes.chapter18.recipe18_03;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;

/**
 *
 * @author Juneau
 */

public class AuthorTaskTwo implements Callable<AuthorInfo>, ManagedTask {
    // The ID of the request to report on demand.
    BigDecimal authorId;
    AuthorInfo authorInfo;
    Map<String, String> execProps;

    public AuthorTaskTwo(BigDecimal id) {
        this.authorId = id;
        execProps = new HashMap<>();
       
        execProps.put(ManagedTask.IDENTITY_NAME, getIdentityName());
    }

    public AuthorInfo call() {
// Find the entity bean and return it to the client. 
        return authorInfo;
    }

    public String getIdentityName() {
        return "AuthorTask: AuthorID=" + authorId;
    }

    public Map<String, String> getExecutionProperties() {
        return execProps;
    }

    public String getIdentityDescription(Locale locale) { 
        // Use a resource bundle...
        return "BookTask asynchronous EJB invoker";
    }

    @Override
    public ManagedTaskListener getManagedTaskListener() {
        return new CustomManagedTaskListener();
    }
}
