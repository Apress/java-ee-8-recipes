
package org.javaee8recipes.chapter09.jsf


import javax.inject.Named

import javax.enterprise.context.SessionScoped
import javax.faces.bean.ManagedBean
/**
 *
 * @author juneau
 */
@Named(name="groovyController")
@SessionScoped
class GroovyController {
    
    def getGroovyBanner(){
        "Hello, this is Groovy"
    }
	
}

