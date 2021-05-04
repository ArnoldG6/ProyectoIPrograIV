package proyecto.presentacion.subjects;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Provider.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import proyecto.model.Model;
import proyecto.model.User;

@WebServlet(name = "SubjectController", urlPatterns = {"/presentation/subjects/register",
"/presentation/subjects/show","/presentation/subjects/image","/presentation/subjects/print"})
@MultipartConfig(location="C:/PROYECTO/images")
public class Controller extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
                //registerSubject(request,response);
            String viewUrl=null;
            switch(request.getServletPath()){
               // case "/presentation/subjects/show":
                 //   viewUrl=this.show(request);
                   // break;              
                case "/presentation/subjects/register":
                    viewUrl=this.registerSubject(request,response);
                    break;            
                //case "/presentation/subjects/image":
                 //   viewUrl=this.image(request,response);
                   // break;
                default: viewUrl = "/index.jsp"; break;
            }
            if(viewUrl!=null)
                request.getRequestDispatcher(viewUrl).forward( request, response);
            
            
        } catch (Exception e) {
            request.getRequestDispatcher("/presentation/Error.jsp").forward(request, response);
        }

    }
    public String registerSubject (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try{
            HttpSession session = request.getSession(true);
            String subId = request.getParameter("subId"),
                   subName = request.getParameter("subName"),
                   subDesc = request.getParameter("subDesc");
            //final Part imagen = request.getPart("subImg");
            Model.getInstance().insertSubject(subId, subName, subDesc);
            //imagen.write(subId);
            session.setAttribute("subjects",  Model.getInstance().getSubjects());
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch(Exception e){
           HttpSession session = request.getSession(true);
           session.setAttribute("exc",e.getMessage());
           throw e;
        }
        return "/index.jsp";
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
    /*
        private String show(HttpServletRequest request) throws Exception {     
            HttpSession session = request.getSession(true);
            User u = (User) session.getAttribute("user");
            request.setAttribute("cursos", Model.getInstance().getSubjectList(u));
            return "/index.jsp";
    }
        
    private String image(HttpServletRequest request,  HttpServletResponse response) throws IOException {     
        String code = request.getParameter("subId");
        Path path = FileSystems.getDefault().getPath("C:/PROYECTO/images", code);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            throw e;
        }
        return null;
    }    
*/
    
    
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