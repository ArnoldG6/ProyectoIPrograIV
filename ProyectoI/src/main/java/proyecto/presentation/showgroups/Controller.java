/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentation.showgroups;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proyecto.model.Group;
import proyecto.model.Model;
import proyecto.model.Student;
import proyecto.model.User;

/**
 *
 * @author victo
 */
@WebServlet(name = "Controller", urlPatterns = {"/presentation/user/teacher/groups"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            String viewUrl = "/index.jsp";
            switch (request.getServletPath()) {
                case "/presentation/user/teacher/groups":
                    viewUrl = this.show(request);
                    break;
                case "/presentation/user/teacher/grades":
                    viewUrl = this.show2(request);
                    break;
                case "/presentation/user/teacher/grade":
                    viewUrl = this.show2(request);
                    break;
                default:
                    viewUrl = "/index.jsp";
                    break;
            }
            request.getRequestDispatcher(viewUrl).forward(request, response);

        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/Error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private String show(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        session.setAttribute("groups", Model.getInstance().getSGroupsMap(u));
        return "/presentation/user/teacher/groups.jsp";
    }

    private String show2(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        HashMap<String, Group> groups = (HashMap<String, Group>) request.getAttribute("groups");
        String G1 = (String) request.getAttribute("pepito");
        List<Student> pepita = groups.get(G1).getStudents();
        session.setAttribute("students", pepita);
        return "/presentation/user/teacher/grades.jsp";
    }

    public void registerStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            String id = request.getParameter("id"),
                    grade = request.getParameter("grade");
            if (id.isEmpty() || grade.isEmpty()) {
                throw new IOException("Ninguno "
                        + "de los campos debe estar vacío.");
            }
            if (Model.getInstance().getUsersMap().get(id) != null) {
                throw new IOException("El usuario digitado ya existe");
            }
            //String pass = Model.getInstance().insertStudent(name,id,email,telNum);
            //request.setAttribute("genPass", pass); 
            //request.getRequestDispatcher("/presentation/register/GenPassword.jsp").forward(request, response);
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            throw e;
        }
    }

}
