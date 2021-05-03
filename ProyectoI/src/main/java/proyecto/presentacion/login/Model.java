/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentacion.login;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.model.User;

public final class Model {

    User current;
    public Model() {
        //this.reset();
    }
    public void updateCurrentUser(User u) throws Exception{
        try {
            proyecto.model.Model.getInstance().setCurrent(u);
        } catch (Exception ex) {
            
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    public User login(String id, String pass) throws Exception{
        try{
            User u = proyecto.model.Model.getInstance().seekUser(id, pass);
            if (u != null){
                current = u;
                updateCurrentUser(u);
            }
        }catch(Exception e){
            throw e;
        }
        return current;
    }
  
    public void reset() {
        setCurrent(new User());
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }


}
