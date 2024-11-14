package org.frank.webapp.servelet.controlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import org.frank.webapp.servelet.models.Entities.User;
import org.frank.webapp.servelet.repositories.UserRepository;
import org.frank.webapp.servelet.services.LoginServices;
import org.frank.webapp.servelet.services.ServiceJDBCException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UserRepository repo;

    @Inject
    private LoginServices loginServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> authUsername = loginServices.getUserName(req);
        if(authUsername.isPresent()){
            try(PrintWriter out = resp.getWriter()){
                out.println( String.format("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Header-request</title>
                        </head>
                        <body>
                            <h1>
                                Hola nuevamente!!
                            </h1>
                            <p>Bienvenido %s, vuestra  esta iniciado exitosamente</p>
                            <a href='%s'>Volver</a>
                        </body>
                        </html>
                        """, authUsername.get(), req.getContextPath()+"/index.html"));
            }
        }else{
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isCorrect = false;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try{
            User user = repo.findByUserName(username);
            if(user != null && user.getPassword().equals(password)){
                isCorrect = true;
            }
        }catch(Exception e){
            throw new ServiceJDBCException(e.getMessage());
        }


        if(isCorrect){
            //Cookie usernameCookie = new Cookie("username", username);
            //resp.addCookie(usernameCookie);
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            
            if(loginServices.getURL(req)!=null){
                resp.sendRedirect(loginServices.getURL(req));
            }
            else{
                resp.sendRedirect(req.getContextPath()+"/login");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, """
                Lo sentimos, usted no esta autorizado para acceder a esta pagina 
                    """);
        }
    }
}
