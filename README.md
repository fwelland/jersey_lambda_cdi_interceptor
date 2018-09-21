CDI Intercepter with JAX-RS/Jersey  (and AWS Lambda)
---------------------------------------------------

This is a follow on to this SO issue:   https://stackoverflow.com/questions/52248208/cdi-interceptors-and-jersey-jax-rs

Basically, I have some JAX-RS controllers that use CDI injectable services.  Some
of the service methods are annotated with CDI interceptor annotations.    

The interceptors do not work.

I first noticed this when I wrapped work like this in a AWS lambda.   

After fumbling about it, I decided to reduce variables, where I can. 
I re-wrapped this work into a Java SE/Jersey standalone thing -- NO EE
container -- to attempt to see if this just a jersey thing or what.    

At present the stand-alone/SE jersey application doesn't fire the interceptor either.  

This https://github.com/fwelland/sample_java_se_cdi_interceptor repo is a 'pure' 
Java SE console app with CDI (via weld).  It is very similar to this sample code
and it does fire the interceptor. 

Project structure is like this: 

* contracts -- defines service api/interfaces
* simpleservice -- implements a service (from contracts)
* jax-rs -- uses only JAX-RS and CDI api to have REST controller using the service
* jersey-lambda -- uses controllers, [AWS Serverles Java Container](https://github.com/awslabs/aws-serverless-java-container), and Jersey to proxy API Gateway REST requests to a JAX-RS controller; CDI support comes from jersey-cdi2-se and WELD 3x. 
* standalone -- uses controllers, simple JDK http server, jersey container to implement REST; CDI support comes from jersey-cdi2-se and WELD 3x. 

Neither jersey-lambda or standalone trigger the interceptor. 

In both cases, logs (or CloudWatch) displays messages like: 
```
WARN: WELD-001478: Interceptor class com.pin.jerseyboot.RequiresNewTransactionalInterceptor is enabled for the application and for the bean archive /blah/blah/.... It will only be invoked in the @Priority part of the chain.
```
Taking out the @Priority or changing the priority value has no affect. 

Yes, I agree, if I were to make a EE WAR and stuff that into a EE Web or Full EE container
I suspect that interceptor would work.

The original motivation for this drill is to see how much code from my existing 
inventory can be recycled into a REST proxied lambda (NO CONTAINER, outside of FAAS/Lambda). 

(NOTE: I am aware it maybe questionable to use some/all/any of the above techs in a FAAS/Lambda environment. I will tackle the 'should I do this' just after I finish answering the 'can I do this'.)


