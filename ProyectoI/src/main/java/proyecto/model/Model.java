package proyecto.model;

import java.util.HashMap;
import proyecto.model.entities.AdministratorDAO;

/**
 *
 * @author arnoldgq
 */
public class Model {

    private static Model instance;
    private HashMap<String, Student> students;
    private HashMap<String, Groups> groups;
    private HashMap<String, Subject> subjects;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Administrator> admins;

    public Model() {
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

    public HashMap<String, Groups> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Groups> groups) {
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
        if (u != null) {
            if (u.valPass(clave)) {
                return u;
            } else {
                throw new Exception("El usuario digitado no existe");
            }

        }
        return null;
    }

    public String showSubjects() {
        return getSubjects().toString();
    }

    public String showSubject(String id) {
        return getSubjects().get(id).show();
    }

    public String randomPass() {
        return "";
    }

    public String insertStudent(String id, String nom, String em, String cllph) {
        return "";
    }

}
