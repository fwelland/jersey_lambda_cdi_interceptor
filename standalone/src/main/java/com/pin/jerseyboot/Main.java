package com.pin.jerseyboot;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import javax.ws.rs.ext.RuntimeDelegate;


public class Main
{
   
    public static void main(final String[] args) 
        throws Exception
    {
        startServer();
        Thread.currentThread().join();
    }
    
    
    private static void startServer()
        throws IOException
    {        
        final HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.stop(0);
            }
        }));
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRSApp(), HttpHandler.class);        
        server.createContext("/", handler);
        server.start();                
    }
}
