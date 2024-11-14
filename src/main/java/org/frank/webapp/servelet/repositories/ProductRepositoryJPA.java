package org.frank.webapp.servelet.repositories;

import java.util.List;

import org.frank.webapp.servelet.models.Entities.Product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
@RepositoryJpa
public class ProductRepositoryJPA implements Repository <Product> {

    @Inject
    private EntityManager em; 

    @Override
    public List<Product> listAll() throws Exception {
        return em.createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) throws Exception {
        return em.find(Product.class, id);
    }

    @Override
    public void save(Product t) throws Exception {
        
        if(t.getId() != null && t.getId()> 0){
            em.merge(t);
        }else{
            em.persist(t);
        }
    
    }

    @Override
    public void delete(long id) throws Exception {
        em.remove(this.findById(id));
    }
    
}
