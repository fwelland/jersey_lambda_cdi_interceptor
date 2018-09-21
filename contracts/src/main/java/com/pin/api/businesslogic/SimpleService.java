package com.pin.api.businesslogic;


public interface SimpleService
{
    public String salute(); 

    public String insertRecord(Integer code)
        throws BadCodeException; 
    
}
