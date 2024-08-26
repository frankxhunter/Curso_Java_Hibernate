package org.frank.webapp.servelet.controlers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.frank.webapp.servelet.services.LoginServices;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject
    private LoginServices loginServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> authUserName = loginServices.getUserName(req);

        if(authUserName.isPresent()){
//            Cookie usernameCookie = new Cookie("username", "");
//            usernameCookie.setMaxAge(0);
//            resp.addCookie(usernameCookie);
            HttpSession session = req.getSession();
            session.invalidate();

        }
        resp.sendRedirect(req.getContextPath()+"/login.html");

    }
}
