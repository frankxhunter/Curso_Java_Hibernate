package org.frank.webapp.servelet.controlers;

import java.io.IOException;
import java.util.Optional;

import org.frank.webapp.servelet.models.Cart;
import org.frank.webapp.servelet.models.ItemCart;
import org.frank.webapp.servelet.models.Product;
import org.frank.webapp.servelet.services.LoginServices;
import org.frank.webapp.servelet.services.ServicesDB;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart/add")
public class AddCarServlet extends HttpServlet {
    @Inject
    private Cart cart;

    @Inject
    private ServicesDB services;

    @Inject
    private LoginServices loginServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(loginServices.isLogin(req) && req.getParameter("id") != null){
            Long id = Long.parseLong(req.getParameter("id"));
            Optional<Product> product = services.findProductById(id);
            if(product.isPresent()){
                ItemCart item = new ItemCart(1 , product.get());
                cart.addItem(item);
                }
            resp.sendRedirect(req.getContextPath()+"/cart/show");
        }
        else {
            resp.sendRedirect(req.getContextPath()+"/login.html");
        }
    }
}
