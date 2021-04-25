/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentacion.login;



import java.io.IOException;
import java.io.PrintWriter;
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
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            //String id = request.getParameter("id");
            //S/tring pass = request.getParameter("pass");
            request.getRequestDispatcher("/presentation/login/login/mostrar_usuario").forward(request,response);
        }catch(Exception e){
            throw e;
        }

  }


     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        
        
    }

        
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    
}
