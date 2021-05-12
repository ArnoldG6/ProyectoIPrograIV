package proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Group {
    private List<Student> students;
    private Subject subj;
    private Teacher teach;
    private int numStu;
    private String nrc;
    private String subName;
    private boolean status;
    private static int count = 0;
    public Group(String nrc, Subject subj, Teacher teach, int numStu) {
        this.students = new ArrayList<>();
        this.subj = subj;
        this.teach = teach;
        this.numStu = numStu;
        this.status = true;
        this.subName = subj.getNameSubj();
        this.nrc = nrc;
    }
    public Group(Subject subj, Teacher teach, int numStu) {
        this.students = new ArrayList<>();
        this.subj = subj;
        this.teach = teach;
        this.numStu = numStu;
        this.status = true;
        this.subName = subj.getNameSubj();
        this.nrc = generateID();
    }
    public final String generateID(){ //forDisplay its only 
        //One example of this sequence is: "HU1234"
        int zerosQuan = 4 - String.valueOf(count).length();
        String nrcNum = String.valueOf(count);
        for(int i = 0; i<zerosQuan; i++) nrcNum = "0"+nrcNum;
        count += 1;
        return subName.charAt(0)+subName.charAt(1)+nrcNum;
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

    public void setSubName(String subName) {
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
        return subj;
    }

    public void setTeacher(Subject teacher) {
        this.subj = teacher;
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
        String f = "", tn = subj.getNameSubj(), tc = subj.getIdSub(), numS = Double.toString(numStu), nr = nrc;
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

    public String getSubName() {
        return subName;
    }

    public void SetSubName(String s) {
        subName = s;
    }

    public Teacher getTeach() {
        return teach;
    }

    public void setTeach(Teacher teach) {
        this.teach = teach;
    }

}
