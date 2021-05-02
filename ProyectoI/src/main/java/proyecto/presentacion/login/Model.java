/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentacion.login;
import proyecto.model.User;

public final class Model {

    User current;
    public Model() {
        this.reset();
    }
    public void updateCurrentUser(User u){
        proyecto.model.Model.getInstance().setCurrent(u);
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
