package com.pin.api.businesslogic;


public class BadCodeException
    extends Exception
{    
    private static final long serialVersionUID = -2257117408218103071L;

    public BadCodeException(String msg)
    {
        super(msg); 
    }
}
