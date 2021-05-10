package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Group {

    private List<Student> students;
    private Subject teacher;
    private int numStu;
    private String nrc;
    private String subName;
    private boolean status;

    public Group(String id,Subject tea, int numS) {
        this.students = new ArrayList<>();
        this.teacher = tea;
        this.numStu = numS;
        this.nrc = id;
        this.status = true;
        this.subName = "Nombre&#160;&#160;del&#160;curso";
    }
    public void insertStudents(Student stu) {
        students.add(stu);
    }

    public void deleteGroups(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
            }
        }
    }
    public void setSubName(String subName){
        this.subName = subName;
    }
    public String showStudent(int pos) {
        return "";
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Subject getTeacher() {
        return teacher;
    }

    public void setTeacher(Subject teacher) {
        this.teacher = teacher;
    }

    public int getNumStu() {
        return numStu;
    }

    public void setNumStu(int numStu) {
        this.numStu = numStu;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String show() {
        String f = "", tn = teacher.getNameSubj(), tc = teacher.getIdSub(), numS = Double.toString(numStu), nr = nrc;
        f = f
                + "Nombre de la materia: " + tn
                + "\n" + "Identificador de materia: " + tc
                + "\n" + "Numero de estudiantes matriculados: " + numS
                + "\n" + "NRC: " + nr
                + "\n" + "Lista de estudiantes:"
                + "\n";
        for (int i = 0; i < students.size(); i++) {
            f += students.get(i).show() + "\n";
        }

        return f;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
