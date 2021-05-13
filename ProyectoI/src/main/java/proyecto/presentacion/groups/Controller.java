package proyecto.presentacion.groups;

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
import proyecto.model.Model;
import proyecto.model.Subject;
import proyecto.model.Teacher;
import proyecto.model.User;

@WebServlet(name = "GroupController", urlPatterns = {"/presentation/RegisterGroup/RegisterGroup",
    "/presentation/RegisterGroup/View"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {

            if ("/presentation/RegisterGroup/View".equals(request.getServletPath())) 
                updateAttributes(request,response);
            
            if ("/presentation/RegisterGroup/RegisterGroup".equals(request.getServletPath())) 
                registerGroup(request, response);
            
            request.getRequestDispatcher("/presentation/user/admin/RegisterGroup.jsp").forward(request, response);

        } catch (Exception e) {
            HttpSession session = request.getSession(true);
            session.setAttribute("exc", e.getMessage());
            //request.getRequestDispatcher("/presentation/user/admin/RegisterGroup.jsp").forward(request, response);
            request.getRequestDispatcher("/presentation/RegisterGroup/View").forward(request, response);
        }

    }

    public void updateAttributes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        HashMap<String, Teacher> teachers = Model.getInstance().getTeachersMap(user);
        HashMap<String, Subject> subjects = Model.getInstance().getSubjectsMap(user);
        if (subjects == null || subjects.size() <= 0) 
            throw new Exception("No hay profesores disponibles para realizar este proceso.");
        
        if (teachers == null || teachers.size() <= 0) 
            throw new Exception("No hay cursos disponibles para realizar este proceso.");
        
        request.setAttribute("teachers", teachers);
        request.setAttribute("subjects", subjects);
    }

    public void registerGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            HttpSession session = request.getSession(true);
            String subId = request.getParameter("subId");
            String teaGroupId = (String) request.getParameter("teaGroupId");
            String nrc = Model.generateGroupID(subId);
            //String message = "subId: "+subId;
            
            
            int subCapacity = Integer.parseInt((String) request.getParameter("subCapacity"));
            Model.getInstance().insertGroup(subId, teaGroupId, subCapacity);
            if (subId.equals("empty")) 
                throw new IOException("Debe seleccionar un curso");
            
            if (teaGroupId.equals("empty")) 
                throw new IOException("Debe seleccionar un profesor");
            
            if (request.getParameter("subCapacity").equals("empty")) 
                throw new IOException("Debe seleccionar una capacidad");
            String message = "Se ha creado el grupo de NRC: " + nrc + " con éxito.";
            session.setAttribute("message", message);
            session.setAttribute("message", message);
            
        } catch (Exception e) {
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
