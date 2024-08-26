package org.frank.webapp.servelet.controlers;

import java.io.IOException;
import java.util.Optional;

import org.frank.webapp.servelet.services.LoginServices;
import org.frank.webapp.servelet.services.ServicesDB;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/delete")
public class ProductDeleteServlet extends HttpServlet {

    @Inject
    private ServicesDB services; 

    @Inject
    private LoginServices loginServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> authUserName = loginServices.getUserName(req);
        if(authUserName.isPresent()){
            Long id = Long.parseLong(req.getParameter("id"));
            services.deleteProduct(id);
            resp.sendRedirect(req.getContextPath()+"/products.html");

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos, solo usuarios autorizados pueden crear nuevos productos");
        }

    }
}
