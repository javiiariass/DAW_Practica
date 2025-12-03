/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daw.controller;

import daw.model.dao.UserDAO;
import daw.model.entity.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Atiende "user/login", "user/register", "user/logout", "users"
 *
 * @author Javi
 */
@WebServlet(name = "UserController", urlPatterns = {"/user/*", "/users"})
public class UserController extends HttpServlet {

    private UserDAO userDAO;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    public void destroy() {
        userDAO.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.log(Level.INFO, "-----------------------------------");
        logger.log(Level.INFO, "Iniciando atención solicitud GET");
        String vista = "index";
        logger.log(Level.INFO, "Vista inicial \"{0}\" ", vista);
        String action = request.getServletPath();
        logger.log(Level.INFO, "path inicial GET \"{0}\" ", action);
        if (request.getPathInfo() != null) {
            action += request.getPathInfo();
        }
        logger.log(Level.INFO, "Path completo GET \"{0}\" ", action);

        //  Lo controlamos en el switch
        // String accion = "/users";
        // if (request.getServletPath().equals("/user")) {
        //     if (request.getPathInfo() != null) {
        //         accion = request.getPathInfo();
        //     } else {
        //         accion = "error";
        //     }
        // }
        logger.log(Level.INFO, "Atendiendo solicitud GET \"{0}\" ", action);

        switch (action) {
            case "/users" -> {
                List<User> lu = userDAO.findAll();
                request.setAttribute("users", lu);
                vista = "user-list";
            }
            case "/user/login" -> {

                vista = "login";
                //request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                //response.sendRedirect(request.getContextPath() + "/index.html");
            }
            case "/user/register" -> {
                vista = "user-form";

                // si usuario logueado ?
                //request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            }
            case "/user/logout" -> {
                logout(request, response);
                vista = "index";
            }
            default ->
                vista = "error";
            // response.sendRedirect(request.getContextPath() + "/index.html");
        }
        request.getRequestDispatcher("/WEB-INF/views/" + vista + ".jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.log(Level.INFO, "-----------------------------------");
        logger.log(Level.INFO, "Iniciando atención solicitud POST");
        String action = request.getServletPath();
        logger.log(Level.INFO, "path inicial GET \"{0}\" ", action);
        if (request.getPathInfo() != null) {
            action += request.getPathInfo();
        }

        logger.log(Level.INFO, "Atendiendo solicitud POST \"{0}\" ", action);
        switch (action) {
            case "/user/save" -> {
                register(request, response);
            }
            case "/user/login" ->
                login(request, response);
            default ->
                response.sendRedirect(request.getContextPath());
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // En MVP es texto plano

        User user = userDAO.findByUsername(username);

        if (user != null && user.getPasswordHash().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Usando una lista podemos incrementar los elementos a futuro de manera sencilla
            List<String> param = new ArrayList();
            param.add(request.getParameter("username"));
            param.add(request.getParameter("email"));
            param.add(request.getParameter("password"));

            // Comprobar que no hay elementos nulos/vacios
            for (String s : param) {
                if (s.trim().isEmpty()) // trim para evitar que " " sea válido
                {
                    throw new NullPointerException();
                }
            }

            // Validacion basica para username único
            if (userDAO.findByUsername(param.get(0)) != null) {
                request.setAttribute("error", "El usuario ya existe");
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
                return;
            }

            // Crear usuario
            User newUser = new User(param.get(0), param.get(1), param.get(2));

            userDAO.create(newUser);
            // Auto-login tras registro

            // Para iniciar sesión
            // HttpSession session = request.getSession();
            // session.setAttribute("user", newUser);
            response.sendRedirect(request.getServletPath());

        } catch (NullPointerException e) {

            // Loguear el error técnico
            logger.log(Level.SEVERE, "Error crítico al registrar usuario", e);
            request.setAttribute("msg", "Error en el formulario: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Loguear el error técnico 
            logger.log(Level.SEVERE, "Error crítico al registrar usuario", e);
            request.setAttribute("msg", "Error al registrar: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(request, response);
        //response.getWriter().println("Lista de usuarios: " + users.size()); // Temporal
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
