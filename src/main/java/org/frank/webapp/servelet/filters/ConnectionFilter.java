package org.frank.webapp.servelet.filters;

import java.io.IOException;

import org.frank.webapp.servelet.services.ServiceJDBCException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    // @Inject
    // @MySQLConnection
    // private Connection connection;

    @Override 
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        // try  {
        //     Connection con = this.connection;
        //     if (con.getAutoCommit()) {
        //         con.setAutoCommit(false);
        //     }
            try {
                chain.doFilter(req, resp);
                // con.commit();
            } catch ( ServiceJDBCException e) {
                // con.rollback();
                ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        e.getMessage());
                e.printStackTrace();
            }

        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
    }

}
