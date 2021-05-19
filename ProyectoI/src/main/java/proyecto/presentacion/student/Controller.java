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
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import proyecto.model.Model;
import proyecto.model.Subject;
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
                    viewUrl = this.getRecord(request, response);
                    break;
                case "/presentation/user/student/constancy":
                    viewUrl = this.Contancy(request, response);
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

    public String Contancy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigo = request.getParameter("codigo");
        Subject curso;
        try {
            /*
            PdfDocument pdfa= new PdfDocument();
            PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
            Document doc = new Document(pdf, PageSize.A4.rotate());
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            doc.add(new Paragraph("CURSO: " + curso.getNameSubj()));
            doc.add(new Paragraph(""));

            doc.close();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline");
             */
            return "/presentation/user/student/constancy.jsp";
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
