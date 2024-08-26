package org.frank.webapp.servelet.Interceptors;

import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;


    @AroundInvoke
    public Object logging(InvocationContext invocation) throws Exception {


        // Antes de invocar el metodo
        System.out.println("//////////////////////////////////////////////////////");
        log.info("****************Se ha invocado el metodo: " + invocation.getMethod().getName() + """
                \nProcedente de la clase:  """ + invocation.getMethod().getDeclaringClass());

        Object result = invocation.proceed();

        // Despues de la invoacion del metodo
        log.info("Saliendo de la invocacion del metodo: " + invocation.getMethod().getName() + """
                \nProcedente de la clase:  """ + invocation.getMethod().getDeclaringClass());

        return result;

    }
}
