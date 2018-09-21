package com.pin.faas;

import java.util.logging.Logger;
import javax.interceptor.*;
import javax.annotation.Priority;
import javax.transaction.Transactional;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class RequiresNewTransactionalInterceptor
{
    private static final Logger LOGGER = Logger.getLogger("RequiresNewTransactionalInterceptor");    
        
    @AroundInvoke
    public Object handleTransactionBoudary(InvocationContext context) 
         throws Exception
    {        
        LOGGER.info("handle transaction boundary...");
        Object result = context.proceed();                           
        return result; 
    } 
}
