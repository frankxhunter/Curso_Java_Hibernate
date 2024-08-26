package org.frank.webapp.servelet.services;

import org.frank.webapp.servelet.models.Category;
import org.frank.webapp.servelet.models.Product;

import java.util.List;
import java.util.Optional;

public interface ServicesJDBC {
    List<Product> listAllProduct();

    List<Category> listAllCategory();

    Optional<Product> findProductById(Long id);

    Optional<Product> findCategoryById(Long id);


    void save(Product p);

    void delete(Long id);

}
