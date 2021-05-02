package proyecto.presentacion.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proyecto.model.User;

@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/login",
    "/presentation/login/logout"})
//@WebServlet(name = "Login_View", urlPatterns = {"/presentation/login/login/mostrar_usuario"})
public class Controller extends HttpServlet {

    Model current;
    String idError = null;
    String passError = null;

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            if (request.getParameter("user") == null) {
                current = new Model();
            }

            switch (request.getServletPath()) {
                case "/presentation/login/login":
                    login(request, response);
                    break;
                case "/presentation/login/logout":
                    logout(request, response);
                    break;
            }
            idError = null;
            passError = null;
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/Error.jsp").forward(request, response);
        }
    }

    protected void login(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            String id = request.getParameter("id"),
                    pass = request.getParameter("pass");
            if (id.equals("") || pass.equals("")) {
                idError = "Debe&#160;digitar&#160;un&#160;nombre&#160;de&#160;usuario.";
                passError = "Debe&#160;digitar&#160;una&#160;contraseña.";
                throw new IOException();
            }

            User user = current.login(id, pass);
            request.setAttribute("user", user);
            request.setAttribute("login_model", current);

        } catch (Exception e) {
            String error1 = "Usuario o contraseña incorrectos".replaceAll(" ","&#160;");
            switch (e.getMessage()) {
                case "El usuario digitado no existe":
                    request.setAttribute("idError", error1);
                    request.setAttribute("passError",error1);
                    break;
                case "La contraseña digitada no es correcta":
                    request.setAttribute("idError", error1);
                    request.setAttribute("passError",error1);
                    break;
                default:
                    request.setAttribute("idError", idError);
                    request.setAttribute("passError", passError);
                    break;

            }
            request.getRequestDispatcher("/presentation/login/View.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            HttpSession session = request.getSession(true);
            session.removeAttribute("user");
            session.removeAttribute("login_model");
            session.invalidate();
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/Error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
