package org.frank.webapp.servelet.services;

import java.util.List;
import java.util.Optional;

import org.frank.webapp.servelet.Interceptors.Logging;
import org.frank.webapp.servelet.Interceptors.TransactionalJdbc;
import org.frank.webapp.servelet.models.Entities.Category;
import org.frank.webapp.servelet.models.Entities.Product;
import org.frank.webapp.servelet.repositories.Repository;
import org.frank.webapp.servelet.repositories.RepositoryJpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServicesDB {

    @Inject
    @RepositoryJpa
    private Repository<Product> productRepository;

    @Inject
    @RepositoryJpa
    private Repository<Category> categoryRepository;

    @Logging
    @TransactionalJdbc
    public List<Product> listAllProduct() {
        try {
            return productRepository.listAll();
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public Optional<Product> findProductById(Long id) {
        try {
            return Optional.of(productRepository.findById(id));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public List<Category> listAllCategory() {
        try {
            return categoryRepository.listAll();

        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.of(categoryRepository.findById(id));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public void saveProduct(Product p) {
        try {
            productRepository.save(p);

        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    public void deleteProduct(Long id) {
        try {
            productRepository.delete(id);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }
}
