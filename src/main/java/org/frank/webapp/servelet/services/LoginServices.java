package org.frank.webapp.servelet.services;

import java.sql.SQLException;
import java.util.Optional;

import org.frank.webapp.servelet.Interceptors.Logging;
import org.frank.webapp.servelet.models.SessionWeb;
import org.frank.webapp.servelet.repositories.SessionReposity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ApplicationScoped 
public class LoginServices {

    @Inject
    private SessionReposity repo;

    public Optional<String> getUserName(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        } else {
            try {
                SessionWeb sessionWeb = repo.findById(session.getId());
                if (sessionWeb != null) {
                    session.setAttribute("username", sessionWeb.getUsername());
                    username = sessionWeb.getUsername();
                }
            } catch (SQLException e) {
                throw new ServiceJDBCException(e.getMessage());
            }
            return username != null ? Optional.of(username) : Optional.empty();
        }
    }

    @Logging
    public boolean isLogin(HttpServletRequest req) {
        return req.getSession().getAttribute("username") != null;
    }

    public void setURl(HttpServletRequest req) {
        String requestedUrl = req.getRequestURI();
        if (req.getQueryString() != null) {
            requestedUrl += "?" + req.getQueryString();
        }
        HttpSession session = req.getSession();
        session.setAttribute("urlOriginal", requestedUrl);

    }

    public String getURL(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (String) session.getAttribute("urlOriginal");
    }
}
