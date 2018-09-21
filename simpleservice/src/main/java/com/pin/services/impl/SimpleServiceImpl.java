package com.pin.services.impl;

import com.pin.api.businesslogic.BadCodeException;
import com.pin.api.businesslogic.SimpleService;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;

@Dependent
public class SimpleServiceImpl
    implements SimpleService
{            
    @Override
    public String salute()
    {
        return("salute");
    }    

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = BadCodeException.class)
    public String insertRecord(Integer code)
        throws BadCodeException
    {
        String r = "insertRecord called with code " + code;         
        if(null != code && -1 == code)
        {
            throw new BadCodeException("exception trigger value happenend, tossing an exception and expecting a rollback");            
        }
        return(r); 
    }
}
