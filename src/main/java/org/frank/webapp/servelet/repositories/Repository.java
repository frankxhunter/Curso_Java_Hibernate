package org.frank.webapp.servelet.repositories;

import java.util.List;

public interface  Repository<T> {
    
    List<T> listAll() throws Exception;
    T findById(Long id) throws Exception;
    void save(T t) throws Exception;
    void delete(long id) throws Exception;
}
    