package org.frank.webapp.servelet.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.frank.webapp.servelet.configs.MySQLConnection;
import org.frank.webapp.servelet.models.Entities.Category;
import org.frank.webapp.servelet.models.Entities.Product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductRepository implements Repository<Product> {

    private Connection con;
    
    @Inject
    public ProductRepository(@MySQLConnection Connection con) {
        this.con = con;
    }

    public void init(){
        System.out.println("Iniciacilizando el beans " + this.getClass().getName());
    }

    @Override
    public List<Product> listAll() throws SQLException {
        List<Product> list = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            ResultSet result = st.executeQuery("""
                        SELECT p.*, c.name AS categoria, c.id as category_id FROM productos AS p
                        INNER JOIN
                        Categorias AS c
                        ON p.id_categoria = c.id
                    """);

            while (result.next()) {
                list.add(getProduct(result));
            }
            result.close();
        }

        return list;
    }

    @Override
    public Product findById(Long id) throws SQLException {
        Product product = new Product();

        String sql = """
                    SELECT p.*, c.name AS categoria, c.id as category_id
                    FROM productos AS p
                    INNER JOIN
                    Categorias AS c
                    ON p.id_categoria = c.id
                    WHERE p.id = ?
                """;

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                product = getProduct(result);
            }
            result.close();
        }
        return product;
    }

    @Override
    public void save(Product t) throws SQLException {
        String sql = "";
        if (t.getId() == null) {
            sql = """
                    INSERT INTO productos
                    (name, price, id_categoria, sku, register_date)
                    VALUES (?, ?, ?, ?, ?)
                    """;
        } else {
            sql = """
                    UPDATE FROM productos
                    set
                    name = ?, price=?, id_categoria=?, sku=?
                    WHERE
                    id = ?
                    """;
        }
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setInt(2, t.getPrice());
            ps.setLong(3, t.getCategoria().getId());
            ps.setString(4, t.getSku());
            if (t.getId() != null)
                ps.setLong(5, t.getId());
            else
                ps.setDate(5, Date.valueOf(t.getRegisterDate()));

            ps.executeUpdate();
        }

    }

    @Override
    public void delete(long id) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement("""
            DELETE FROM PRODUCTOS 
            WHERE id = ?
        """)){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }


    private Product getProduct(ResultSet result) throws SQLException {
        Product product = new Product();
        product.setId(result.getLong("id"));
        product.setName(result.getString("name"));
        product.setSku(result.getString("sku"));
        product.setRegisterDate(result.getDate("register_date").toLocalDate());
        product.setPrice(result.getInt("price"));
        product.setCategoria(new Category(result.getLong("category_id"), result.getString("categoria")));

        return product;
    }

}
