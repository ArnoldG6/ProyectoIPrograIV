package proyecto.model;

import java.io.IOException;
import java.util.HashMap;
import proyecto.model.entities.AdministratorDAO;
import proyecto.model.entities.StudentDAO;

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
        if ((AdministratorDAO.getInstance().getCount()
            + StudentDAO.getInstance().getCount()) 
            != getUsersMap().size()) {
            Model.getInstance().setAdmins(AdministratorDAO.getInstance().listAll());
            //this.getInstance().setTeachers(TeacherDAO.getInstance().listAll());
            Model.getInstance().setStudents(StudentDAO.getInstance().listAll());
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
        if (instance == null) 
            instance = new Model();
        return instance;
    }

    HashMap<String, User> getUsersMap() throws Exception {
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
        if (u != null) 
            if (u.valPass(clave)) 
                return u;
             else 
                throw new IOException("La contraseña digitada no es correcta");
        else 
            throw new IOException("El usuario digitado no existe");
    }
    public String insertStudent(String name, String id, String email, String telNum)
            throws Exception{
        String pass = "";
        try{
            Student stu = new Student(name, id, email, telNum, "");
            pass = Student.generateRandomPassword(4);
            stu.setPass(pass);
            StudentDAO.getInstance().add(stu.getId(), stu);
            updateModel();
            return pass;
            
        }catch(Exception e){
            //throw new IOException("Este ID ya está registrado");
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
            //System.out.println("Value = " + value.getNameSubj());
            if (value.getNameSubj().equals(name)) 
                idSub = value.getIdSub();
        }
        return idSub;
    }
    
    public Student searchStudent(String id) {
        String idSub = "";
        Student s=null;
        for (Student value : students.values()) {
            //System.out.println("Value = " + value.getNameSubj());
            if (value.getId().equals(id)) 
                s = value;
        }
        return s;
    }

    public Teacher searchTeacher(String id) {
        //iterando solo sobre valores
        for (Teacher value : teachers.values()) {
            System.out.println("Value = " + value.getId());
            return value;
        }
        return null;
    }

    public String insertTeacher(String nom, String id, String em, String cllph) {
        Teacher tc = new Teacher(nom, id, em, cllph, "");
        String pss = tc.generateRandomPassword(4);
        tc.setPass(pss);
        //teachers.put(id, tc);
        return pss;
    }

    public String insertAdmin(String nom, String id, String em, String cllph) throws Exception {
        Administrator ad = new Administrator(nom, id, em, cllph, "");
        String pss = ad.generateRandomPassword(4);
        ad.setPass(pss);
        //admins.put(id, ad);
        
        return pss;
    }

    public String insertGroup( String n, Teacher tea, int numS) {
        //Teacher tea, double numS, String n
        Group gp = new Group(n, tea, numS);
        //groups.put(n, gp);
        return "";
    }
    
    public void insertGrpSt(String id, Group g){
         searchStudent(id).insertGrp(g);
    }
    
    public void setCurrent(User u){
        this.current = u;
    }
    
    
}
