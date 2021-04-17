package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Student extends User {
    
    public List<Groups> groups;

    public Student(String name, String id, String email, String telNum) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telNum = telNum;
        groups = new ArrayList<>();
        type = "STU";
    }

    public Student() {
        this("", "", "", "");
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }

}
