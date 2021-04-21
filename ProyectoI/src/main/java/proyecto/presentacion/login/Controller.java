/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentacion.login;


import proyecto.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jsanchez
 */
@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/login"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {
      
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath()){             
            case "/presentation/login/login":
                viewUrl=this.login(request);
                break;            
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    private String login(HttpServletRequest request) { 
        try{
            //Map<String,String> errores =  this.validar(request);
            //if(errores.isEmpty()){
                this.updateModel(request);          
                return this.loginAction(request);
            //}
            //else{
                //request.setAttribute("errores", errores);
                //return "/presentation/login/View.jsp"; 
            //}            
        }
        catch(Exception e){
            return "/presentation/Error.jsp";             
        }         
    }
    
    Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("id").isEmpty()){
            errores.put("id","Cedula requerida");
        }

        if (request.getParameter("pass").isEmpty()){
            errores.put("pass","Clave requerida");
        }
        return errores;
    }
    
    void updateModel(HttpServletRequest request){
        Model model= (Model) request.getAttribute("model"); 
        model.getCurrent().setId(request.getParameter("id"));
        model.getCurrent().setPass(request.getParameter("pass"));
   }

        
    public String loginAction(HttpServletRequest request) {
        Model model= (Model) request.getAttribute("model");
        proyecto.model.Model  domainModel =  proyecto.model.Model.getInstance();
        HttpSession session = request.getSession(true);
        try {
            User real = domainModel.seekUser(model.getCurrent().getId(),model.getCurrent().getPass());
            if(real == null) throw new Exception();
            session.setAttribute("user", real);
            String viewUrl="";
            switch(real.getType()){
                case 0:
                    viewUrl="/ProyectoI/";
                    break;
                case 1:
                    viewUrl="/ProyectoI/";
                    break;
                case 2:
                     viewUrl="/ProyectoI/";
                    break;      
                case 3:
                     viewUrl="/ProyectoI/";
                    break; 
            }
            return viewUrl;
        } catch (Exception ex) {
            //Map<String,String> errores = new HashMap<>();
            //request.setAttribute("errores", errores);
            //errores.put("id","Usuario o clave incorrectos");
            //errores.put("pass","Usuario o clave incorrectos");
            return "/presentation/login/View.jsp"; 
        }        
    }   

    public String logout(HttpServletRequest request){
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "/presentation/Index.jsp";   
    }

    public String show(HttpServletRequest request){
        return this.showAction(request);
    }
        
    public String showAction(HttpServletRequest request){
        Model model= (Model) request.getAttribute("model");
        model.getCurrent().setId("");
        model.getCurrent().setPass("");
        return "/presentation/login/View.jsp"; 
    }    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        
        
    }

        
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
