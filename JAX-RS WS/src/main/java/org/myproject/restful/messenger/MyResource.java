//How to speed up tomcat startup.

//Tomcat 8.5. Inside catalina.properties, located in the /conf directory set:
// tomcat.util.scan.StandardJarScanFilter.jarsToSkip=\*.jar

// Or go into context.xml, located in Tomcat's /conf directory and add:
//<JarScanner scanClassPath="false"/>

// # String cache configuration.
// #NOP by KTL tomcat.util.buf.StringCache.byte.enabled=true

package org.myproject.restful.messenger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}
