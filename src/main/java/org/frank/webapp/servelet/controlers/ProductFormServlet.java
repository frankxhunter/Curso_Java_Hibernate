package org.frank.webapp.servelet.controlers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.frank.webapp.servelet.models.Entities.Category;
import org.frank.webapp.servelet.models.Entities.Product;
import org.frank.webapp.servelet.services.ServicesDB;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/new")
public class ProductFormServlet extends HttpServlet {

    @Inject
    private ServicesDB serv;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> list = serv.listAllCategory();
        req.setAttribute("listCategory", list);
        getServletContext().getRequestDispatcher("/forms/formNewProduct.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String name = req.getParameter("name");
            String sku = req.getParameter("sku");
            int price = Integer.parseInt(req.getParameter("price"));
            System.out.println(price);
            Long categoryId = Long.parseLong(req.getParameter("category"));
            LocalDate registerDate = LocalDate.parse(req.getParameter("date"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // Validar todos los datos

            // Guardar los datos
            Optional<Category> optionalCategory = serv.findCategoryById(categoryId);
            Category category = null;
            if (optionalCategory.isPresent())
                category = optionalCategory.get();
            else
                throw new NumberFormatException("El id de la categoria no existe");

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setSku(sku);
            product.setCategoria(category);
            product.setRegisterDate(registerDate);

            serv.saveProduct(product);

            resp.sendRedirect(req.getContextPath() + "/products.html");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

}
