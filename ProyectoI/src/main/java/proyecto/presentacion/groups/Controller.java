package proyecto.presentacion.groups;
import proyecto.presentacion.register.*;
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

@WebServlet(name = "RegisterController", urlPatterns = {"/presentation/register/RegisterGroup"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
             HttpSession session = request.getSession(true);
             registerGroup(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/register/View.jsp").forward(request, response);
        }

    }
    public void registerGroup (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
             HttpSession session = request.getSession(true);
             
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