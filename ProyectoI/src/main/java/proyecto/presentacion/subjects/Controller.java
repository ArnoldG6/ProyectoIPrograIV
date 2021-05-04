package proyecto.presentacion.subjects;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proyecto.model.Model;
import proyecto.model.Subject;

@WebServlet(name = "SubjectController", urlPatterns = {"/presentation/subjects/register"})

public class Controller extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
            registerSubject(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/Error.jsp").forward(request, response);
        }

    }
    public void registerSubject (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
            HttpSession session = request.getSession(true);
            String subId = request.getParameter("subId"),
                   subName = request.getParameter("subName"),
                   subDesc = request.getParameter("subDesc");
            Image subImg = (Image) request.getPart("subImg");
            Model.getInstance().insertSubject(subId, subName, subDesc, subImg);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch(Exception e){
           ArrayList<String> errors = new ArrayList<>();
           errors.add(e.getMessage());
           request.setAttribute("errors",errors);
           throw e;
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
