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
import javax.servlet.http.HttpSession;
import proyecto.model.Model;
import proyecto.model.User;

/**
 *
 * @author GONCAR4
 */
@WebServlet(name = "RegisterTeacherController", urlPatterns = {"/presentation/register/RegisterTeachers", 
    "/presentation/user/admin/ListTeachers"})
public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, Exception {
        try{
            String viewURL = "/presentation/user/admin/ListRegisterTeachers.jsp";
            switch(request.getServletPath()){
                case "/presentation/user/admin/ListTeachers":
                    viewURL=this.show(request);
                    break;              
                case "/presentation/register/RegisterTeachers":
                    viewURL=this.registerTeacher(request,response);
                    break;
                default: viewURL = "/presentation/user/admin/ListRegisterTeachers.jsp";
            }
            request.getRequestDispatcher(viewURL).forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/Error.jsp").forward(request, response);
        }
    }

    public String registerTeacher (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
            String name = request.getParameter("regTeaNom"),  
                    id = request.getParameter("regTeaId"), 
                    email =   request.getParameter("regTeaEmail"), 
                    telNum = request.getParameter("regTeaTel");
            if(name.isEmpty() || id.isEmpty() || email.isEmpty()
                    ||telNum.isEmpty()) throw new IOException("Ninguno "
                            + "de los campos debe estar vacÃ­o.");
            String pass = Model.getInstance().insertTeacher(name,id,email,telNum);
            request.setAttribute("genPass", pass); 
            request.getRequestDispatcher("/presentation/register/GenPassword.jsp").forward(request, response);
        }catch(Exception e){
           ArrayList<String> errors = new ArrayList<>();
           errors.add(e.getMessage());
           request.setAttribute("errors",errors);
           throw e;
        }
        return "/presentation/register/RegisterTeachers.jsp";
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
    
    private String show(HttpServletRequest request) throws Exception {     
            HttpSession session = request.getSession(true);
            User u = (User) session.getAttribute("user");
            session.setAttribute("teachers", Model.getInstance().getTeachersMap(u));
            return "/presentation/user/admin/ListTeachers.jsp";
    }
    
}