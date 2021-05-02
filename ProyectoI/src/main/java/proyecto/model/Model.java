package proyecto.model;

import java.io.IOException;
import java.util.HashMap;
import proyecto.model.entities.AdministratorDAO;

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

    public Model() {
        current = null;
        students = new HashMap<>();
        groups = new HashMap<>();
        subjects = new HashMap<>();
        teachers = new HashMap<>();
        admins = new HashMap<>();
        /*
        //Some dummy objects in order to test the login and logout feature.
        students.put("1234", new Student("Juan Papu", "1234", "jp@gmail.com", "555", "1234"));
        teachers.put("4321", new Teacher("Cristian Aguilar", "4321", "ca@gmail.com", "665", "5678"));
        admins.put("0", new Administrator("admin", "0", "root0@gmail.com", "123", "1111"));
         */
    }

    public final void updateModel() throws Exception {
        if (AdministratorDAO.getInstance().getCount() != admins.size()) {
            this.getInstance().setAdmins(AdministratorDAO.getInstance().listAll());
            //this.getInstance().setAdmins(AdministratorDAO.getInstance().listAll());
            //this.getInstance().setTeachers(TeacherDAO.getInstance().listAll());
            //this.getInstance().setStudents(StudentDAO.getInstance().listAll());
            //this.getInstance().setSubjects(SubjectsDAO.getInstance().listAll());
            //this.getInstance().setAdmins(AdministratorDAO.getInstance().listAll());
        }
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

    HashMap<String, User> getUsersMap() throws Exception {
        HashMap<String, User> users = new HashMap<String, User>();
        try {
            updateModel();
            users.putAll(getStudents());
            users.putAll(getAdmins());
            users.putAll(getTeachers());
        } catch (Exception e) {
            throw e;
        }

        return users;
    }

    public User seekUser(String cedula, String clave) throws Exception {
        HashMap<String, User> users = getUsersMap();
        User u = users.get(cedula);
        if (u != null) 
            if (u.valPass(clave)) 
                return u;
             else 
                throw new IOException("La contraseña digitada no es correcta");
        else 
            throw new IOException("El usuario digitado no existe");
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
        //iterando solo sobre valores
        String idSub = "";
        for (Subject value : subjects.values()) {
            //System.out.println("Value = " + value.getNameSubj());
            if (value.getNameSubj().equals(name)) {
                idSub = value.getIdSub();
            }
        }
        return idSub;
    }

    public String insertStudent(String nom, String id, String em, String cllph) {
        Student st = new Student(nom, id, em, cllph, "");
        String pss = st.generateRandomPassword(4);
        st.setPass(pss);
        students.put(id, st);
        return pss;
    }

    public String searchTeacher(String name) {
        //iterando solo sobre valores
        for (Teacher value : teachers.values()) {
            System.out.println("Value = " + value.getName());
            return value.getName();
        }
        return "";
    }

    public String insertTeacher(String nom, String id, String em, String cllph) {
        Teacher tc = new Teacher(nom, id, em, cllph, "");
        String pss = tc.generateRandomPassword(4);
        tc.setPass(pss);
        teachers.put(id, tc);
        return pss;
    }

    public String insertAdmin(String nom, String id, String em, String cllph) {
        Administrator ad = new Administrator(nom, id, em, cllph, "");
        String pss = ad.generateRandomPassword(4);
        ad.setPass(pss);
        admins.put(id, ad);
        return pss;
    }

    public String insertGroup(Teacher tea, double numS, String n) {
        //Teacher tea, double numS, String n
        Group gp = new Group(tea, numS, n);
        groups.put(n, gp);
        return "";
    }
    public void setCurrent(User u){
        this.current = u;
    }
}
