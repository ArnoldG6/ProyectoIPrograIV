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

@WebServlet(name = "StudentController", urlPatterns = {"/presentation/subjects/register"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            String viewUrl = "/index.jsp";
            switch (request.getServletPath()) {
                case "/presentation/subjects/show":
                    viewUrl = this.show(request);
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
            if(user == null) throw new Exception("Sesión expirada");
            session.setAttribute("studentGroups", Model.getInstance().getAvailableGroups(user));
            return "/presentation/user/student/enroll.jsp";
        
        } catch (Exception e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exc", e.getMessage());
            throw e;
        }
    }

    public String getRecord(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            HttpSession session = request.getSession(true);
        } catch (Exception e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exc", e.getMessage());
            throw e;
        }
        return "/presentation/user/student/record.jsp";
    }

    public String searchSubject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            HttpSession session = request.getSession(true);
            String searchCriteria = (String) request.getParameter("searchCriteria");
            if (searchCriteria == null) {
                throw new IOException("El campo de búsqueda no debe estar vacío");
            }
            session.setAttribute("searchedSbjts",
                    Model.getInstance().searchSubjects((User) session.getAttribute("user"),
                            searchCriteria));
        } catch (Exception e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exc", e.getMessage());
            throw e;
        }
        return "/presentation/subjects/search.jsp";
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

    private String show(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        session.setAttribute("subjects", Model.getInstance().getSubjectsMap(u));
        return "/presentation/mainpage.jsp";
    }

    private String image(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String subId = request.getParameter("subId");
        Path path = FileSystems.getDefault().getPath("C:/PROYECTO", subId);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            throw e;
        }
        return null;
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
