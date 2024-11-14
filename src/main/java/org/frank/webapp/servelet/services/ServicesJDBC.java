package org.frank.webapp.servelet.services;

import java.util.List;
import java.util.Optional;

import org.frank.webapp.servelet.models.Entities.Category;
import org.frank.webapp.servelet.models.Entities.Product;

public interface ServicesJDBC {
    List<Product> listAllProduct();

    List<Category> listAllCategory();

    Optional<Product> findProductById(Long id);

    Optional<Product> findCategoryById(Long id);


    void save(Product p);

    void delete(Long id);

}
