package com.pin.rest;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingController
{                      
    private static final Logger LOGGER = Logger.getLogger("JerseyCDIInterceptor");
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping()
    {    
        LOGGER.info("ping");
        return Response.status(200).entity("ping").build();        
    }                         
}