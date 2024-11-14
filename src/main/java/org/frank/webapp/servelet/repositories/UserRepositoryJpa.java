package org.frank.webapp.servelet.repositories;

import java.util.List;

import org.frank.webapp.servelet.models.Entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
@RepositoryJpa
public class UserRepositoryJpa implements Repository<User>{
    
    @Inject
    private EntityManager em; 

    @Override
    public List<User> listAll() throws Exception {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User findById(Long id) throws Exception {
        return em.find(User.class, id);
    }

    public User findByUsername(String username) throws Exception {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
        .setParameter("username", username).getSingleResult();
    }

    @Override
    public void save(User t) throws Exception {
        
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
