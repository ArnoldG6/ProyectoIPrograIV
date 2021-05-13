package proyecto.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import proyecto.model.entities.AdministratorDAO;
import proyecto.model.entities.GROUPS_STUDENTSDAO;
import proyecto.model.entities.GroupDAO;
import proyecto.model.entities.StudentDAO;
import proyecto.model.entities.SubjectDAO;
import proyecto.model.entities.TeacherDAO;

/**
 *
 * @author arnoldgq
 */
public class Model {

    private User current;
    private static Model instance;
    private HashMap<String, Student> students;
    private HashMap<String, Group> groups;
    private HashMap<String, Subject> subjects;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Administrator> admins;
    private final static String SUB_STATUS = "OFERTA";

    public Model() {
        current = null;
        students = new HashMap<>();
        groups = new HashMap<>();
        subjects = new HashMap<>();
        teachers = new HashMap<>();
        admins = new HashMap<>();
    }

    public HashMap<String, Subject> getSubjectsMap(User u) throws Exception {
        try {
            updateModel();
            //HashMap<String, Subject> result = new HashMap<String, Subject>();
            HashMap<String, Subject> result = SubjectDAO.getInstance().listAll();
            result.putAll(subjects);
            if (u != null) {
                if (u.getType() == 3) {
                    return result; //admin
                }
                result.values().removeIf(s -> !s.getStatus().equals("OFERTA"));
                return result;
            } else {
                result.values().removeIf(s -> !s.getStatus().equals("OFERTA"));
                return result;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public HashMap<String, Group> getSGroupsMap(User u) throws Exception {
        try {
            updateModel();
            //HashMap<String, Subject> result = new HashMap<String, Subject>();
            HashMap<String, Group> result = GroupDAO.getInstance().listGroup(u.getId());
            if (u != null) {
                if (u.getType() == 1) {
                    return result; //admin
                }
                return result;
            } else {
                return result;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public final void updateModel() throws Exception {

        Model.getInstance().setAdmins(AdministratorDAO.getInstance().listAll());
        Model.getInstance().setTeachers(TeacherDAO.getInstance().listAll());
        Model.getInstance().setStudents(StudentDAO.getInstance().listAll());
        Model.getInstance().setSubjects(SubjectDAO.getInstance().listAll());
        Model.getInstance().setGroups(GroupDAO.getInstance().listAll());

    }

    public void linkStudentGroup(Student s, Group g, Float grade) throws Exception {
        if (s == null || g == null || grade == null) {
            throw new Exception("Null pointer in linkStudentGroup method");
        }
        if (!g.getStudents().contains(s)) {
            g.getStudents().add(s);
        }
        if (!s.getGroups().contains(g)) {
            s.getGroups().add(g);
        }
        Pair<Group, Float> gra = new Pair<>(g, grade);
        if (!s.getGrades().contains(gra)) {
            s.getGrades().add(gra);
        }
    }

    public void updateStudentsGroups() throws Exception {
        try {
            HashMap<String, Subject> result = new HashMap< String, Subject>();
            ArrayList<String[]> groupsHasStu = GROUPS_STUDENTSDAO.getInstance().listAll();
            for (int i = 0; i < groupsHasStu.size(); i++) {
                linkStudentGroup(
                        students.get(groupsHasStu.get(i)[0]),
                        groups.get(groupsHasStu.get(i)[1]),
                        Float.parseFloat(groupsHasStu.get(i)[2]));
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public HashMap<String, Subject> searchSubjects(User u, String pattern) throws Exception {
        try {
            Subject value;
            pattern = pattern.toUpperCase();
            HashMap<String, Subject> result = new HashMap< String, Subject>();
            for (Map.Entry<String, Subject> entry : getSubjectsMap(u).entrySet()) {
                value = subjects.get(entry.getKey());
                if (value.getDesc().toUpperCase().contains(pattern)
                        || value.getIdSub().toUpperCase().contains(pattern)
                        || value.getNameSubj().toUpperCase().contains(pattern)) {
                    result.put(value.getIdSub(), value);
                }
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    List<Pair<Group, Float>> getHistory(String stuId) throws IOException {
        try {
            if (students.get(stuId) != null) {
                return students.get(stuId).getGrades();
            }
            throw new IOException("El ID del estudiante digitado no existe");
        } catch (Exception e) {
            throw e;
        }

    }

    public HashMap<String, Teacher> getTeachersMap(User u) throws Exception {
        try {
            updateModel();
            //HashMap<String, Subject> result = new HashMap<String, Subject>();
            HashMap<String, Teacher> result = TeacherDAO.getInstance().listAll();
            result.putAll(teachers);
            if (u != null) 
                if (u.getType() == 3) 
                    return result;
                
            
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static void setInstance(Model aInstance) {
        instance = aInstance;
    }

    public HashMap<String, Student> getStudents() {
        return students;
    }

    public void setStudents(HashMap<String, Student> students) {
        this.students = students;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

    public HashMap<String, Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<String, Subject> subjects) {
        this.subjects = subjects;
    }

    public HashMap<String, Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(HashMap<String, Teacher> teachers) {
        this.teachers = teachers;
    }

    public HashMap<String, Administrator> getAdmins() {
        return admins;
    }

    public void setAdmins(HashMap<String, Administrator> admins) {
        this.admins = admins;
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public HashMap<String, User> getUsersMap() throws Exception {
        HashMap<String, User> users = new HashMap<>();
        try {
            users.putAll(getStudents());
            users.putAll(getAdmins());
            users.putAll(getTeachers());
            return users;
        } catch (Exception e) {
            throw e;
        }

    }

    public User seekUser(String cedula, String clave) throws Exception {
        updateModel();
        HashMap<String, User> users = getUsersMap();
        User u = users.get(cedula);
        if (u != null) {
            if (u.valPass(clave)) {
                return u;
            } else {
                throw new IOException("La contraseña digitada no es correcta");
            }

        } else {
            throw new IOException("El usuario digitado no existe");
        }
    }

    public String insertStudent(String name, String id, String email, String telNum)
            throws Exception {
        String pass = "";
        try {
            Student stu = new Student(name, id, email, telNum, "");
            pass = Student.generateRandomPassword(4);
            stu.setPass(pass);
            StudentDAO.getInstance().add(stu.getId(), stu);
            updateModel();
            return pass;

        } catch (Exception e) {
            //throw new IOException("Este ID ya está registrado");
            throw e;
        }
    }

    public void insertSubject(String subId, String subName,
            String subDesc)
            throws Exception {
        try {
            Subject s = new Subject(subId, subName, subDesc, SUB_STATUS);
            SubjectDAO.getInstance().add(s);
            updateModel();
        } catch (Exception e) {

            throw e;
        }
    }

    public String showSubjects() {
        return getSubjects().toString();
    }

    public String showTeachers() {
        return getTeachers().toString();
    }

    public String showHistory(String id) {
        return getStudents().get(id).show();
    }

    public String showSubject(String id) {
        return getSubjects().get(id).show();
    }

    public String showGrps(String id) {
        //return getTeachers().get(id);
        return "";
    }

    public String searchSubject(String name) {
        String idSub = "";
        for (Subject value : subjects.values()) {
            if (value.getNameSubj().equals(name)) {
                idSub = value.getIdSub();
            }
        }
        return idSub;
    }

    public Student searchStudent(String id) throws Exception {
        updateModel();
        return students.get(id);
    }

    public Teacher searchTeacher(String id) throws Exception {
        updateModel();
        return teachers.get(id);
    }

    public static final String generateGroupID(String sub) throws Exception {
        try{
            return Group.generateID(sub);
        }catch(Exception e){
            throw e;
        }
    }

    public String insertTeacher(String nom, String id, String em, String cllph) throws Exception {
        String pass;
        try {
            Teacher tea = new Teacher(nom, id, em, cllph, "");
            pass = User.generateRandomPassword(4);
            tea.setPass(pass);
            TeacherDAO.getInstance().add(tea.getId(), tea);
            updateModel();
            return pass;

        } catch (Exception e) {
            //throw new IOException("Este ID ya está registrado");
            throw e;
        }
    }

    public String insertAdmin(String nom, String id, String em, String cllph) throws Exception {
        Administrator ad = new Administrator(nom, id, em, cllph, "");
        String pss = Administrator.generateRandomPassword(4);
        ad.setPass(pss);
        updateModel();
        return pss;
    }

    public void insertGroup(String subID, String teachID, int numStu) throws IOException, Exception {
        try {
            updateModel();//Model needs to updated in order to search for subjects
            Subject subj = subjects.get(subID);
            Teacher teach = teachers.get(teachID);
            if (teach == null) 
                throw new IOException("Error al insertar el grupo. Profesor no encontrado");
            if (subj == null) 
                throw new IOException("Error al insertar el grupo. Curso no encontrado");
            GroupDAO.getInstance().add(new Group(subj, teach, numStu));
            updateModel();//After adding the group to the DB it is necessary to update the model again
        } catch (Exception e) {
            throw e;
        }
    }

    public void insertGrpSt(String id, Group g) throws Exception {
        searchStudent(id).insertGrp(g);

    }

    public void setCurrent(User u) {
        this.current = u;
    }

}
