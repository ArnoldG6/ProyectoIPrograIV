package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Student extends User {

    public List<Groups> groups;

    public Student(String name, String id, String email, String telNum, String pas) {
        this.name = name;
        this.id = id.toUpperCase();
        this.email = email;
        this.telNum = telNum;
        this.pass = pas;
        this.groups = new ArrayList<>();
        this.type = "STU";
    }

    public Student() {
        this("", "", "", "", "");
    }

    public String show() {
        String f = "", tn = name, tc = id, numS = email, nr = telNum;
        f = f + "Nombre del estudiante: " + tn
                + "\n" + "Correo del estudiante: " + tc
                + "\n" + "Correo: " + numS
                + "\n" + "Numero de telefono: " + nr
                + "\n";
        return f;
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }

}
