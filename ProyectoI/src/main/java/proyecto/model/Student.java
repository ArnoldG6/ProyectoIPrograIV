package proyecto.model;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author victo
 */
public class Student extends User {

    public List<Group> groups;
    public List<Pair<Group, Float>> grades;

    public Student(String name, String id, String email, String telNum, String pass) {
        this.username = name;
        this.id = id.toUpperCase();
        this.email = email;
        this.telNum = telNum;
        this.pass = pass;
        this.groups = new ArrayList<>();
        this.grades = new ArrayList<Pair<Group,Float>>();
        this.type = 2;
    }
    
    //Nota: validar el rango de la nota
    //public void insertGrade(){
    //    grades.add(new Pair<Groups,Integer>(groups.get(1),1));
    //}
    public List<Group> getGroups(){return groups;}
    List<Pair<Group, Float>> getGrades(){return grades;}
    public Student() {
        this("", "", "", "", "");
    }
    
    public void insertGrp(Group g){
        groups.add(g);
    }

    public String show() {
        String f = "", tn = username, tc = id, numS = email, nr = telNum;
        f = f + "Nombre del estudiante: " + tn
                + "\n" + "Correo del estudiante: " + tc
                + "\n" + "Correo: " + numS
                + "\n" + "Numero de telefono: " + nr
                + "\n";
        return f;
    }

    public String showGroups() {
        String f = "";
        f = f
                + "\n" + "Historial:"
                + "\n";
        for (int i = 0; i < groups.size(); i++) {
            f += groups.get(i).show() + "\n";
        }
        return f;
    }

}
