
package com.pin.jerseyboot;

import com.pin.rest.PingController;
import com.pin.rest.TxController;
import java.util.*;
import javax.ws.rs.core.Application;

public class JaxRSApp
    extends Application
{
    
    @Override
    public Set<Class<?>> getClasses()
    {
        HashSet<Class<?>> c = new HashSet<>();        
        c.add(PingController.class);
        c.add(TxController.class); 
        return(Collections.unmodifiableSet(c));
    }    
}
