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

/**
 *
 * @author Javi
 */
@WebServlet(name = "UserController", urlPatterns = {"/login", "/registro", "/logout", "/usuarios", "/user/*", "/users"})
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
        String vista;
        String action = request.getServletPath();



        //  Lo controlamos en el switch
        // String accion = "/users";
        // if (request.getServletPath().equals("/user")) {
        //     if (request.getPathInfo() != null) {
        //         accion = request.getPathInfo();
        //     } else {
        //         accion = "error";
        //     }
        // }
        

        logger.log(Level.INFO, "Atendiendo solicitud");

        switch (action) {
            case "/users" -> {
                List <User> lu = userDAO.findAll();
                request.setAttribute("users",lu);
                vista = "users";
            }
            case "/login" -> {
                logger.log(Level.INFO, "Atendiendo solicitud \"{0}\" ",action);
                System.out.println("entrando /app/login ");
                //request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/index.html");
            }
            case "/registro" -> {
                // si usuario logueado ?
                //request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            }
            case "/logout" ->
                logout(request, response);
            case "/usuarios" ->
                listUsers(request, response);
            default ->
                response.sendRedirect(request.getContextPath() + "/index.html");
        }
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

        String action = request.getServletPath();

        switch (action) {
            case "/app/login":
                login(request, response);
                break;
            case "/app/registro":
                register(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/index.html");
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // En MVP es texto plano

        User user = userDAO.findByUsername(username);

        if (user != null && user.getPasswordHash().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/index.html");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validacion basica
        if (userDAO.findByUsername(username) != null) {
            request.setAttribute("error", "El usuario ya existe");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(username, email, password);
        // Por defecto el rol es USER (definido en el constructor/entidad)

        try {
            userDAO.create(newUser);
            // Auto-login tras registro
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        // TODO: Crear vista de lista de usuarios
        // request.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(request, response);
        response.getWriter().println("Lista de usuarios: " + users.size()); // Temporal
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
