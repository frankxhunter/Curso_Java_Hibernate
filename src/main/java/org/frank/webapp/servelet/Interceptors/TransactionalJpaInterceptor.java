package org.frank.webapp.servelet.Interceptors;

import org.frank.webapp.servelet.services.ServiceJDBCException;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager con;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {

        try {
            System.out.println("-------------> Iniciando transaction a traves de intecerptores en el metodo "
                    + invocationContext.getClass().getName() + " de la clase "
                    + invocationContext.getMethod().getDeclaringClass());

            con.getTransaction().begin();
            Object result = invocationContext.proceed();
            con.getTransaction().commit();

            System.out.println("-------------> Finalizando la transaction a traves de intecerptores en el metodo "
                    + invocationContext.getClass().getName() + " de la clase "
                    + invocationContext.getMethod().getDeclaringClass());

            return result;
        } catch (ServiceJDBCException e) {
            con.getTransaction().rollback();
            throw e;
        }
    }
}
