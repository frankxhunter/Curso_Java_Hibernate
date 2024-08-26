package org.frank.webapp.servelet.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la aplicacion ////////////////////////////////////////////////////////////////////////");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("message", "This is a message of app");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Destruyendo la aplicacion//////////////////////////////////////////////////////////////////////////");
    }
    
    @Override
    public void requestInitialized(ServletRequestEvent sce) {
        sce.getServletContext().log("Inicializando el reques //////////////////////////////////////////////////////////////////////////////");
        sce.getServletRequest().setAttribute("message", "This is a message of request");
    }
    
    @Override
    public void requestDestroyed(ServletRequestEvent sce) {
        sce.getServletContext().log("Destruyendo la request//////////////////////////////////////////////////////////////////////////");
    }

    @Override
    public void sessionCreated(HttpSessionEvent sce) {
        // Cart cart = new Cart();
        // HttpSession session = sce.getSession();
        // session.setAttribute("cart", cart);
        servletContext.log("Inicializando la session /////////////////////////////////////////////////////////////////////////");
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent sce) {
        servletContext.log("Destruyendo la session /////////////////////////////////////////////////////////////////////////");
    }
}
