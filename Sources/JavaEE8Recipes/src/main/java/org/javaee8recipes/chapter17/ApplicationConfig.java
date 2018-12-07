package org.javaee8recipes.chapter17;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

// Used in Recipe 17-04
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "${'java:global/JavaEE8Recipes/acmedb'}",
        callerQuery = "#{'select password from caller_store where name = ?'}",
        groupsQuery = "select group_name from caller_groups where caller_name = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}",
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3072",
            "${applicationConfig.hash}"
        } // just for test / example
)
@ApplicationScoped
@Named
public class ApplicationConfig {

    public String[] getHash() {
        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }
}
