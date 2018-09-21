package com.pin.faas;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Boot
{
    private static final Logger LOGGER = Logger.getLogger("JerseyCDIInterceptor");                         
                
    public void initObserver(@Observes @Initialized(ApplicationScoped.class) Object o)
    {
        LOGGER.info("application/CDI boot up triggered");
    }          
}
