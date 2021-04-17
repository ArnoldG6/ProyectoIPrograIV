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

    //public Groups() {//Sobre insertar por parametro si hacerlo o crear la lista nada mas?
    //    this();
    //}
    
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

}
