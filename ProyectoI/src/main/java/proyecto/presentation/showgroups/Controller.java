/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentation.showgroups;

import java.io.IOException;
import java.util.HashMap;
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
@WebServlet(name = "Controller", urlPatterns = {"/presentation/user/teacher/groups",
    "/presentation/user/teacher/grades"})
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
                    viewUrl = this.registerGrade(request, response);
                    break;
                default:
                    viewUrl = "/index.jsp";
                    break;
            }
            request.getRequestDispatcher(viewUrl).forward(request, response);

        } catch (Exception e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exc", e.getMessage());
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
        System.out.println("SHOW");
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        session.setAttribute("groups", Model.getInstance().getGroupsMap(u));
        return "/presentation/user/teacher/groups.jsp";
    }

    private String show2(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        HashMap<String, Group> groups = (HashMap<String, Group>) session.getAttribute("groups");
        User user = (User) session.getAttribute("user");
        String groupID = request.getParameter("groupID");
        if(groupID == null) throw new Exception("Group ID Exception");
        HashMap<String,Student> students = groups.get(groupID).getStudents();
        session.setAttribute("students", students);
        return "/presentation/user/teacher/grades.jsp";
    }

    public String registerGrade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String url = "";
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
            HashMap<String, Group> groups = (HashMap<String, Group>) request.getAttribute("groups");
            String G1 = (String) request.getAttribute("pepito");
            //List<Student> pepita = groups.get(G1).getStudents();
            //Student nigga = groups.get(G1).SearchStu(id);
            //nigga.insertNote(groups.get(G1), Float.parseFloat(grade));
            return "/presentation/user/teacher/grades.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

}
