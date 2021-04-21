package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Teacher extends User {

    public List<Groups> groups;

    public Teacher(String name, String id, String email, String telNum, String pas) {
        this.name = name;
        this.id = id.toUpperCase(); //Ej: a00 vs A00
        this.email = email;
        this.telNum = telNum;
        this.pass = pas;
        this.groups = new ArrayList<>();
        this.type = 1;
    }

    public Teacher() {
        this("", "", "", "", "");
    }

 
}
