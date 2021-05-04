package proyecto.presentacion.teachers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyecto.model.Model;

/**
 *
 * @author GONCAR4
 */
@WebServlet(name = "RegisterTeacherController", urlPatterns = {"/presentation/register/RegisterTeachers"})
public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, Exception {
        try{
            registerTeacher(request,response);
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/register/RegisterTeachers.jsp").forward(request, response);
        }

    }

    public void registerTeacher (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
            String name = request.getParameter("regTeaNom"),  
                    id = request.getParameter("regTeaId"), 
                    email =   request.getParameter("regTeaEmail"), 
                    telNum = request.getParameter("regTeaTel");
            if(name.isEmpty() || id.isEmpty() || email.isEmpty()
                    ||telNum.isEmpty()) throw new IOException("Ninguno "
                            + "de los campos debe estar vacío.");
            String pass = Model.getInstance().insertTeacher(name,id,email,telNum);
            request.setAttribute("genPass", pass); 
            request.getRequestDispatcher("/presentation/register/GenPassword.jsp").forward(request, response);
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
