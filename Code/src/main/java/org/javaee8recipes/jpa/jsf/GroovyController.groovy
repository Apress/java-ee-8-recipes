
package org.javaeerecipes.jpa.jsf


import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean
/**
 *
 * @author juneau
 */
@ManagedBean(name="groovyController")
@SessionScoped 
class GroovyController {
    
    def getGroovyBanner(){
        "Hello, this is Groovy"
    }
	
}

