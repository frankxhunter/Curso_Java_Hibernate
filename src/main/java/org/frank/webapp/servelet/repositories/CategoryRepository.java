package org.frank.webapp.servelet.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.frank.webapp.servelet.configs.MySQLConnection;
import org.frank.webapp.servelet.models.Entities.Category;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryRepository implements Repository<Category> {

    @Inject
    @MySQLConnection
    private Connection con;

    public CategoryRepository() {}

    @Override
    public List<Category> listAll() throws SQLException {
        List<Category> list = new ArrayList<>();
        try (Statement st = con.createStatement()) {
            ResultSet result = st.executeQuery("""
                        SELECT * FROM Categorias
                    """);
            while (result.next()) {
                list.add(getCategoria(result));
            }

            result.close();

        }

        return list;
    }

    @Override
    public Category findById(Long id) throws SQLException {
        Category category = new Category();
        try (PreparedStatement ps = con.prepareStatement("""
                    SELECT * FROM Categorias
                    WHERE id = ?
                """)) {
            ps.setLong(1, id);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                category = getCategoria(result);
            }
            result.close();

        }
        return category;
    }

    @Override
    public void save(Category t) throws SQLException {

        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(long t) throws SQLException {

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Category getCategoria(ResultSet result) throws SQLException {
        Category category = new Category();
        category.setId(result.getLong("id"));
        category.setName(result.getString("name"));

        return category;
    }
}
