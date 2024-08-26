package org.frank.webapp.servelet.filters;
import java.io.IOException;
import java.util.Optional;

import org.frank.webapp.servelet.services.LoginServices;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter({"/cart/*", "/product/new", "/product/delete"})
public class LoginFilter implements Filter {

    @Inject
    private LoginServices loginServices;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        Optional<String> username = loginServices.getUserName((HttpServletRequest)req);
        if(username.isPresent()){
            chain.doFilter(req, resp);
        }else{
            //((HttpServletResponse)resp).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, usted no esta autorizado para acceder a esta infomacion");
            loginServices.setURl((HttpServletRequest)req);
            ((HttpServletResponse)resp).sendRedirect(((HttpServletRequest)req).getContextPath()+"/login");

        }
    }
    
}
