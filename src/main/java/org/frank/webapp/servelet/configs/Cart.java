package org.frank.webapp.servelet.configs;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

@SessionScoped
@Named
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Cart {
    
}
