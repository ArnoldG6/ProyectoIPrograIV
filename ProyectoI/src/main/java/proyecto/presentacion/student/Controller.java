package proyecto.presentacion.student;

import proyecto.presentacion.subjects.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import proyecto.model.Model;
import proyecto.model.Student;
import proyecto.model.User;

@WebServlet(name = "StudentController", urlPatterns = {"/presentation/student/enroll",
    "/presentation/student/matricular", "/presentation/user/student/record"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            String viewUrl = "/index.jsp";
            switch (request.getServletPath()) {
                case "/presentation/student/enroll":
                    viewUrl = this.enroll(request, response);
                    break;
                case "/presentation/student/matricular":
                    viewUrl = this.getEnroll(request);
                    break;
                case "/presentation/user/student/record":
                    System.out.println("Jesucristo 2");
                    viewUrl = this.getRecord(request, response);
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

    public String enroll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("user");
            String subID = (String) request.getParameter("subID");
            if (user == null) {
                return "/presentation/login/View.jsp";
            }
            System.out.println(Model.getInstance().getAvailableGroups(user, subID).toString());
            session.setAttribute("studentGroups", Model.getInstance().getAvailableGroups(user, subID));

            return "/presentation/user/student/enroll.jsp";

        } catch (Exception e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exc", e.getMessage());
            throw e;
        }
    }

    private String getEnroll(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        String GID = request.getParameter("GID");
        if (GID == null || user == null) {
            throw new Exception("Exception");
        }
        Model.getInstance().insertStudentBase(user.getId(), GID);
        System.out.println("Students de group toString: " + Model.getInstance().getGroups().get(GID).getStudents().toString());
        System.out.println("Groups de students toString: " + Model.getInstance().getStudents().get(user.getId()).getGroups().toString());
        return "/presentation/user/student/enrolledCorrectly.jsp";
    }

    public String getRecord(HttpServletRequest request, HttpServletResponse response)throws Exception {
        System.out.println("SHOW");
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        System.out.println("User ID: " + u.getId());
        System.out.println("Groups toString: " + Model.getInstance().getGroupsMapS(u).toString());
        
        session.setAttribute("groups", Model.getInstance().getGroupsMapS(u));
        return "/presentation/user/student/record.jsp";

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(proyecto.presentacion.login.Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(proyecto.presentacion.login.Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
