package org.frank.webapp.servelet.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.frank.webapp.servelet.configs.MySQLConnection;
import org.frank.webapp.servelet.models.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserRepository implements Repository<User> {
    @Inject
    @MySQLConnection
    private Connection con;

    public UserRepository() {
    }

    @Override
    public List<User> listAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }

    @Override
    public void delete(long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public User findById(int id) throws SQLException {
        User user = new User();

        String sql = """
                    SELECT * FROM usuarios AS u
                    WHERE u.id = ?
                """;

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                user = getUser(result);
            }
            result.close();
        }
        return user;
    }

    public User findByUserName(String username) throws SQLException {
        User user = null;

        String sql = """
                    SELECT * FROM usuarios AS u
                    WHERE u.username = ?
                """;

        try (PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, username);
            ResultSet result = st.executeQuery();

            while (result.next()) {
                user = getUser(result);
            }
            result.close();
        }
        return user;
    }

    @Override
    public void save(User t) throws SQLException {
        String sql = "";
        if (t.getId() == 0) {
            sql = """
                    INSERT INTO usuarios
                    (username, password)
                    VALUES (?, ?, ?, ?, ?)
                    """;
        } else {
            sql = """
                    UPDATE FROM usuarios
                    SET
                    name = ?, password=?
                    WHERE
                    id = ?
                    """;
        }
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getPassword());
            if (t.getId() != 0)
                ps.setLong(3, t.getId());
            ps.executeUpdate();
        }

    }

    private User getUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getInt("id"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));
        return user;
    }

    @Override
    public User findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}