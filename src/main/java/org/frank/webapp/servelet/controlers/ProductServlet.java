package org.frank.webapp.servelet.controlers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.frank.webapp.servelet.models.Product;
import org.frank.webapp.servelet.services.LoginServices;
import org.frank.webapp.servelet.services.ServicesDB;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/products.html", "/products" })
public class ProductServlet extends HttpServlet {

    @Inject
    private ServicesDB ps;

    @Inject
    private LoginServices loginServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = ps.listAllProduct();

        Optional<String> authUsername = loginServices.getUserName(req);

        req.setAttribute("products", products);
        req.setAttribute("username", authUsername);

        getServletContext().getRequestDispatcher("/showProducts.jsp").forward(req, resp);

    }
}
