

package org.javaee8recipes.chapter17.recipe17_03;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;


/**
 * Entity class User
 */
//@Entity

//@Table(name = "USER")

public class User implements Serializable {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private BigDecimal userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "SECURITY_ROLE")
    private String securityRole;
    
    /** Creates a new instance of User */
    public User() {
    }

    /**
     * Creates a new instance of User with the specified values.
     * @param userId the userId of the User
     */
    public User(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * Gets the userId of this User.
     * @return the userId
     */
    public BigDecimal getUserId() {
        return this.userId;
    }

    /**
     * Sets the userId of this User to the specified value.
     * @param userId the new userId
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of this User.
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of this User to the specified value.
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

  

    /**
     * Gets the securityRole of this User.
     * @return the securityRole
     */
    public String getSecurityRole() {
        return this.securityRole;
    }

    /**
     * Sets the securityRole of this User to the specified value.
     * @param securityRole the new securityRole
     */
    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.userId != null ? this.userId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this User.  The result is 
     * <code>true</code> if and only if the argument is not null and is a User object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User)object;
        if (this.userId != other.userId && (this.userId == null || !this.userId.equals(other.userId))) return false;
        return true;
    }

    
}
