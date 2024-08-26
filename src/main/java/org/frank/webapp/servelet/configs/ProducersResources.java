package org.frank.webapp.servelet.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

@Dependent
public class ProducersResources {

    @Resource(name = "jdbc/ConnectionMySQL")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MySQLConnection
    private Connection beanConnection() throws SQLException {
        // Context initContext;
        // Connection conn = null;
        // try {
        //     initContext = new InitialContext();
        //     Context envContext = (Context) initContext.lookup("java:/comp/env");
        //     DataSource ds = (DataSource) envContext.lookup("jdbc/ConnectionMySQL");
        //     conn = ds.getConnection();
        // } catch (NamingException e) {
        //     e.printStackTrace();
        // }
        // // etc

        return ds.getConnection();
    }

    @Produces
    @Dependent // toma el contexto de la clase donde es llamado 
    private Logger beanLoger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes @MySQLConnection Connection connection) throws SQLException{
        connection.close();
        System.out.println("Cerrando la Conexion a la base de datos");
        
    }

}
