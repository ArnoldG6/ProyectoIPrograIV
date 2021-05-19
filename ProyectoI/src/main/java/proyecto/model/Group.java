package proyecto.model;
import java.util.HashMap;

/**
 *
 * @author victo
 */
public class Group {

    private HashMap<String,Student> students;
    private Subject subj;
    private Teacher teach;
    private int numStu;
    private String nrc;
    private String subName;
    private boolean status;
    private static int count = 0;

    public Group(String nrc, Subject subj, Teacher teach, int numStu) {
        this.students = new HashMap<String,Student>();
        this.subj = subj;
        this.teach = teach;
        this.numStu = numStu;
        this.subName = subj.getNameSubj();
        if(subj.getStatus().equals("OFERTA"))
            this.status = true;
        else
            this.status = false;
        this.nrc = nrc;
    }

    public Group(Subject subj, Teacher teach, int numStu) {
        this.students = new HashMap<String,Student>();
        this.subj = subj;
        this.teach = teach;
        this.numStu = numStu;
        this.status = true;
        this.subName = subj.getNameSubj();
        this.nrc = generateID();
    }

    public final String generateID() {
        Group.count += 1;
        return subName.charAt(0) + subName.charAt(1) + String.valueOf(count);

    }

    public static final String generateID(String sub) throws Exception { //only for displaying the generated id
        try {
            int countCopy = Group.count + 1;
            return sub.charAt(0) + sub.charAt(1) + String.valueOf(count);
        } catch (Exception e) {
            throw e;
        }

    }

    public void insertStudents(Student stu) {
        students.put(stu.getId(),stu);
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String showStudent(int pos) {
        return "";
    }

    public HashMap<String, Student> getStudents() {
        return students;
    }

    public void setStudents(HashMap<String, Student> students) {
        this.students = students;
    }

    public Subject getSubject() {
        return subj;
    }

    public void setTeacher(Subject subj) {
        this.subj = subj;
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



    public String isStatus() {
        return subj.getStatus();
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
