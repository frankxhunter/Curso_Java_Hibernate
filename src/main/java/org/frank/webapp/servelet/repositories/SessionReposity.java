package org.frank.webapp.servelet.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.frank.webapp.servelet.configs.MySQLConnection;
import org.frank.webapp.servelet.models.SessionWeb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SessionReposity {

    @Inject
    @MySQLConnection
    private Connection con;

    public SessionReposity() {
    }

    public SessionWeb findById(String id) throws SQLException {
        SessionWeb session = null;
        try (PreparedStatement ps = con.prepareStatement("""
                    SELECT * FROM Sesiones
                    WHERE id = ?
                """)) {
            ps.setString(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                session = getSession(result);
            }
        }
        return session;
    }

    public void save(SessionWeb t) throws SQLException {
        if (findById(t.getId()) == null) {
            try (PreparedStatement ps = con.prepareStatement("""
                    INSERT INTO Sesiones (id, username) VALUES(?, ?)
                    """)) {
                        ps.setString(1, t.getId());
                        ps.setString(2, t.getUsername());
                        ps.executeUpdate();
            }

        }
    }

    public void delete(String id) throws SQLException {
            try (PreparedStatement ps = con.prepareStatement("""
                    DELETE FROM Sesiones WHERE id = ?
                    """)) {
                        ps.setString(1, id);
            }
    }

    public SessionWeb getSession(ResultSet result) throws SQLException {
        SessionWeb session = new SessionWeb();
        session.setId(result.getString("id"));
        session.setUsername(result.getString("username"));
        return session;
    }
}
