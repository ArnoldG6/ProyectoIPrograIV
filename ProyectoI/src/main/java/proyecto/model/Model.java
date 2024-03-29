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

    public final void updateModel() throws Exception {
        Model.getInstance().setAdmins(AdministratorDAO.getInstance().listAll());
        Model.getInstance().setTeachers(TeacherDAO.getInstance().listAll());
        Model.getInstance().setStudents(StudentDAO.getInstance().listAll());
        Model.getInstance().setSubjects(SubjectDAO.getInstance().listAll());
        Model.getInstance().setGroups(GroupDAO.getInstance().listAll());
        Model.getInstance().matchGroupsTeachers();
        Model.getInstance().matchGroupsSubjects();
        Model.getInstance().matchGroupsStudents();
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

    public HashMap<String, Group> getAvailableGroups(User user, String subID) throws Exception {
        updateModel();
        HashMap<String, Group> groups = Model.getInstance().subjects.get(subID).getGroups();
        if (groups == null) {
            throw new IOException("No se ha encontrado el curso");
        }
        return groups;
    }

    public HashMap<String, Group> getGroupsMap(User u) throws Exception {
        try {
            updateModel();
            if (u != null) {
                if (teachers.get(u.getId()) != null) {
                    return teachers.get(u.getId()).getGroups();
                } else {
                    throw new Exception("Debe ser usuario de tipo profesor");
                }
            }
            throw new Exception("La sesión ha expirado");
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Group> getGroupsMapS(User u) throws Exception {
        try {
            updateModel();
            if (u != null) {
                if (students.get(u.getId()) != null) {
                    return students.get(u.getId()).getGroups();
                } else {
                    throw new Exception("Debe ser usuario de tipo estudiante");
                }
            }
            throw new Exception("La sesión ha expirado");
        } catch (Exception e) {
            throw e;
        }
    }

    public void linkStudentGroup(Student s, Group g, Float grade) throws Exception {
        if (s == null || g == null || grade == null) {
            throw new Exception("Null pointer in linkStudentGroup method");
        }
        if (g.getStudents().get(s.getId()) == null) {
            g.getStudents().put(s.getId(), s);
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

    public void matchGroupsTeachers() throws Exception {
        try {
            Teacher teacher;
            Group group;
            for (Map.Entry<String, Teacher> entry : Model.getInstance().teachers.entrySet()) {
                teacher = Model.getInstance().teachers.get(entry.getKey());
                for (Map.Entry<String, Group> g : Model.getInstance().groups.entrySet()) {
                    group = Model.getInstance().groups.get(g.getKey());
                    if (group.getTeach().getId().equals(teacher.getId())) {
                        teacher.insertGroup(group);
                        group.setTeach(teacher);
                    }

                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void matchGroupsSubjects() throws Exception {
        try {
            Subject sub;
            Group group;
            for (Map.Entry<String, Subject> entry : Model.getInstance().subjects.entrySet()) {
                sub = Model.getInstance().subjects.get(entry.getKey());
                for (Map.Entry<String, Group> g : Model.getInstance().groups.entrySet()) {
                    group = Model.getInstance().groups.get(g.getKey());
                    if (group.getSubject().getIdSub().equals(sub.getIdSub())) {
                        sub.getGroups().put(group.getNrc(), group);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void insertStudentBase(String idStu, String idGr) throws Exception {
        try {
            updateModel();
            Student s = students.get(idStu);
            Group g = groups.get(idGr);
            System.out.println("Id s:" + idStu);
            System.out.println("Id g:" + idGr);
            System.out.println("S:" + s.toString());
            System.out.println("G:" + g.toString());
            if (s == null || g == null) {
                throw new Exception("Exception insertar en intermedia");
            }
            GROUPS_STUDENTSDAO.getInstance().add(idGr, idStu, "0");
        } catch (Exception e) {
            throw e;
        }
    }

    public void matchGroupsStudents() throws Exception {
        try {
            ArrayList<String[]> result = GROUPS_STUDENTSDAO.getInstance().listAll();
            Student studentuno;
            Group group;
            String a,b,c;
            for (Map.Entry<String, Student> entry : Model.getInstance().students.entrySet()) {
                studentuno = Model.getInstance().students.get(entry.getKey());
                for (Map.Entry<String, Group> g : Model.getInstance().groups.entrySet()) {
                    group = Model.getInstance().groups.get(g.getKey());
                    for (int i = 0; i < result.size(); i++) {
                        a=result.get(i)[0];
                        b=result.get(i)[1];
                        c=result.get(i)[2];
                        if(studentuno.getId().equals(b) && group.getNrc().equals(a)){
                            studentuno.getGroups().add(group);
                            studentuno.getGrades().add(new Pair<Group,Float> (group,Float.parseFloat(c)));
                            group.insertStudents(studentuno);
                        }
                    }
                }
            }
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
            if (u != null) {
                if (u.getType() == 3) {
                    return result;
                }
            }

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

    public HashMap<String, Subject> getSubjects() throws Exception {
        return Model.getInstance().subjects;
    }

    public void setSubjects(HashMap<String, Subject> subjects) {
        this.subjects = subjects;
    }

    public HashMap<String, Teacher> getTeachers() throws Exception {
        return Model.getInstance().teachers;
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
        try {
            return Group.generateID(sub);
        } catch (Exception e) {
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
            if (teach == null) {
                throw new IOException("Error al insertar el grupo. Profesor no encontrado");
            }
            if (subj == null) {
                throw new IOException("Error al insertar el grupo. Curso no encontrado");
            }
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
