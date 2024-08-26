package org.frank.webapp.servelet.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.frank.webapp.servelet.Interceptors.Logging;
import org.frank.webapp.servelet.models.Category;
import org.frank.webapp.servelet.models.Product;
import org.frank.webapp.servelet.repositories.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServicesDB {

    @Inject
    private Repository<Product> productRepository;

    @Inject
    private Repository<Category> categoryRepository;

    @Logging
    public List<Product> listAllProduct() {
        try {
            return productRepository.listAll();

        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public Optional<Product> findProductById(Long id) {
        try {
            return Optional.of(productRepository.findById(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public List<Category> listAllCategory() {
        try {
            return categoryRepository.listAll();

        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.of(categoryRepository.findById(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public void saveProduct(Product p) {
        try {
            productRepository.save(p);

        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public void deleteProduct(Long id) {
        try {
            productRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }
}
