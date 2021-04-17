package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Student extends User {
    
    public List<Groups> groups;

    public Student(String na, String ID, String e, String telNum) {
        this.name = na;
        this.id = ID;
        this.email = e;
        this.telephoneNumber = telNum;
        groups = new ArrayList<>();
    }

    public Student() {
        this("", "", "", "");
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }

}
