package org.frank.webapp.servelet.repositories;

import java.util.List;

import org.frank.webapp.servelet.models.Entities.Category;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
@RepositoryJpa
public class CategoriaRepositoryJpa implements Repository<Category> {


    @Inject
    private EntityManager em;

    @Override
    public List<Category> listAll() throws Exception {
        return em.createQuery("FROM Categorias", Category.class).getResultList();
    }

    @Override
    public Category findById(Long id) throws Exception {
       return em.find(Category.class, id);
    }

    @Override
    public void save(Category t) throws Exception {
        if(t.getId() != null && t.getId()> 0){
            em.merge(t);
        }
        else{
            em.persist(t);
        }
    }

    @Override
    public void delete(long id) throws Exception {
        em.remove(this.findById(id));
    }
    
}
