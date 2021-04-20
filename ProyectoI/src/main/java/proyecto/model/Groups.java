package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Groups {

    private List<Student> students;
    private Teacher teacher;
    private double numStu;
    private String nrc;

    public Groups(Teacher tea, double numS, String n) {
        this.students = new ArrayList<>();
        this.teacher = tea;
        this.numStu = numS;
        this.nrc = n;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public double getNumStu() {
        return numStu;
    }

    public void setNumStu(double numStu) {
        this.numStu = numStu;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String show() {
        String f = "", tn = teacher.getName(), tc = teacher.getEmail(), numS = Double.toString(numStu), nr = nrc;
        f = f 
                + "Nombre del profesor: " + tn
                + "\n" + "Correo del profesor: " + tc
                + "\n" + "Numero de estudiantes matriculados: " + numS
                + "\n" + "NRC: " + nr
                + "\n" + "Lista de estudiantes:" 
                + "\n";
        for (int i = 0; i < students.size(); i++) {
            f += students.get(i).show() + "\n";
        }

        return f;
    }

}
