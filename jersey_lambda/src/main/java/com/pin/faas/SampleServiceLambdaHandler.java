package com.pin.faas; 
import com.amazonaws.serverless.proxy.jersey.JerseyLambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger; 
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class SampleServiceLambdaHandler
    implements RequestStreamHandler
{
    static
    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$s %3$s %5$s%6$s%n");            
    }
    
    private static final Logger LOGGER = Logger.getLogger("JerseyCDIInterceptor");    
    private static final ResourceConfig APP = new ResourceConfig().packages("com.pin.rest").register(JacksonFeature.class);
    private static final JerseyLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> HANDLER = JerseyLambdaContainerHandler.getAwsProxyHandler(APP);                
    
                         
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context)
            throws IOException
    {       
        LOGGER.info("handleRequest fired!");
        HANDLER.proxyStream(input, output, context);    
        output.close();
        LOGGER.info("handleRequest is done!");        
    }                        
}