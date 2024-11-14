package org.frank.webapp.servelet.Interceptors;

import java.sql.Connection;

import org.frank.webapp.servelet.configs.MySQLConnection;
import org.frank.webapp.servelet.services.ServiceJDBCException;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MySQLConnection
    private Connection con;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {

        if (con.getAutoCommit()) {
            con.setAutoCommit(false);
        }
        try {
            System.out.println("-------------> Iniciando transaction a traves de intecerptores en el metodo " + invocationContext.getClass().getName()+ " de la clase "+ invocationContext.getMethod().getDeclaringClass());
            Object result = invocationContext.proceed();

            con.commit();

            System.out.println("-------------> Finalizando la transaction a traves de intecerptores en el metodo " + invocationContext.getClass().getName()+ " de la clase "+ invocationContext.getMethod().getDeclaringClass());

            return result;
        } catch (ServiceJDBCException e) {
            con.rollback();
            throw e;
        }
    }
}
