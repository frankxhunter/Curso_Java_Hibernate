package org.frank.webapp.servelet.services;

public class ServiceJDBCException extends RuntimeException{

    public ServiceJDBCException(String msg){
        super(msg);
    }

    public ServiceJDBCException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
