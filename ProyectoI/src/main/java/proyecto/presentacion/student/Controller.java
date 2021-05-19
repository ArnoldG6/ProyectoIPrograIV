package proyecto.presentacion.student;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import javafx.util.Pair;
import proyecto.model.Group;
import proyecto.model.Model;
import proyecto.model.Student;
import proyecto.model.User;

@WebServlet(name = "StudentController", urlPatterns = {"/presentation/student/enroll",
    "/presentation/student/matricular", "/presentation/user/student/record",
"/presentation/user/student/constancy"})
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
                    viewUrl = this.getRecord(request, response);
                    break;
                case "/presentation/user/student/constancy":
                    viewUrl = this.constancy(request, response);
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
        return "/presentation/user/student/enrolledCorrectly.jsp";
    }

    public String getRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        session.setAttribute("groups", Model.getInstance().getGroupsMapS(u));
        return "/presentation/user/student/record.jsp";
    }

    public String constancy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("user");
            Student stu = (Student) user;
            PdfDocument pdf = new PdfDocument();
            PdfWriter pdfw = PdfWriter.getInstance(pdf,response.getOutputStream());
            Document doc = new Document(new Rectangle(PageSize.A4.rotate()));
            for(int i = 0; i<stu.getGrades().size(); i++) {
                 doc.add(new Paragraph("NRC: "+stu.getGrades().get(i).getKey().getNrc()));
                 doc.add(new Paragraph("PROFESOR: "+stu.getGrades().get(i).getKey().getTeach().getId()
                 +" "+ stu.getGrades().get(i).getKey().getTeach().getName()));
                 doc.add(new Paragraph("NOTA: " + String.valueOf(stu.getGrades().get(i).getValue())));
            }
           
            doc.close();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline");
             
            return "/presentation/user/student/record.jsp";
        } catch (Exception ex) {
            return "/presentation/Error.jsp";
        }
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
