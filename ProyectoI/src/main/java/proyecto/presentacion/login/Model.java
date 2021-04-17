/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.presentacion.login;

import proyecto.model.Administrator;
import proyecto.model.Student;
import proyecto.model.Teacher;
import proyecto.model.User;

/**
 *
 * @author jsanchez
 */
public final class Model {
    User current;

    public Model() {
        this.reset();
    }
    
    public void reset(){ 
       switch(current.getType()){
           case "ADM": setCurrent(new Administrator()); break;
           case "STU": setCurrent(new Student()); break;
           case "TEA": setCurrent(new Teacher()); break;
       }    
    }
    
    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }
   
}
