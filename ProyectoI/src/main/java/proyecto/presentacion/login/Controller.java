/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentacion.login;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jsanchez
 */
@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/login"})
//@WebServlet(name = "Login_View", urlPatterns = {"/presentation/login/login/mostrar_usuario"})
public class Controller extends HttpServlet {
  Model current;
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException, Exception {
     current = new Model();
     try{
        current.login(request.getParameter("id"), request.getParameter("pass"));   
        request.setAttribute("user", current);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
     }catch(Exception e){
         request.getRequestDispatcher("/presentation/Error.jsp").forward(request,response);
     }
  }
  


     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try {
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

   
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try {
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        
        
    }

        
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    
}
