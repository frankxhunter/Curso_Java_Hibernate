package org.frank.webapp.servelet.repositories;

import java.sql.SQLException;
import java.util.List;

public interface  Repository<T> {
    
    List<T> listAll() throws SQLException;
    T findById(Long id) throws SQLException;
    void save(T t) throws SQLException;
    void delete(long id) throws SQLException;
}
