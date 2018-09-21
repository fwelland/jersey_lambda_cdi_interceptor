package com.pin.rest;
import com.pin.api.businesslogic.BadCodeException;
import com.pin.api.businesslogic.SimpleService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/tx")
public class TxController
{                      
    private static final Logger LOGGER = Logger.getLogger("JerseyCDIInterceptor");
    
    @Inject
    private SimpleService service; 
            

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testAtTransactional(@QueryParam("c") Integer code)
    {    
        Integer val = 1; 
        LOGGER.info("testAtTransactional invoked");
        if(null == code)
        {
            LOGGER.info("testAtTransactional DID NOT get a code value, assuming value = " + val);
        }
        else
        {
            val = code; 
            LOGGER.info("testAtTransactional DID get a code value = " + val);
        }     
        String result = "called service.insertRecordIntoXferLog(" + val +  ")"; 
        try
        {
            String s = service.insertRecord(val); 
            LOGGER.info(s);
        }
        catch(BadCodeException bce)
        {
            result = bce.getMessage(); 
        }
        catch(Exception other)
        {
            result = other.getMessage(); 
            LOGGER.log(Level.SEVERE,"ERROR",other); 
        }
        return Response.status(200).entity(result).build();                
    }    
}