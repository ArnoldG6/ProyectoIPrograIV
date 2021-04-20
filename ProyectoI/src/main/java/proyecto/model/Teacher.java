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
        this.id = id;
        this.email = email;
        this.telNum = telNum;
        this.pass = pas;
        this.groups = new ArrayList<>();
        this.type = "TEA";
    }

    public Teacher() {
        this("", "", "", "", "");
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }
}
