package proyecto.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arnoldgq
 */
public class Model {

    private static Model instance;
    HashMap<String, Student> students;
    HashMap<String, Groups> groups;
    HashMap<String, Subject> subjects;
    HashMap<String, Teacher> teachers;
    HashMap<String, Administrator> admins;

    public Model() {
        students = new HashMap<String, Student>();
        groups = new HashMap<String, Groups>();
        subjects = new HashMap<String, Subject>();
        teachers = new HashMap<String, Teacher>();
        admins = new HashMap<String, Administrator>();

        //Some dummy objects in order to test the login and logout feature.
        students.put("1234", new Student("Juan Papu", "1234", "jp@gmail.com", "555", "1234"));
        teachers.put("4321", new Teacher("Cristian Aguilar", "4321", "ca@gmail.com", "665", "5678"));
        admins.put("0", new Administrator("admin", "0", "root0@gmail.com", "123", "1111"));
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    HashMap<String, User> getUsersList(){
        HashMap<String, User> users = new HashMap<String, User>();
        users.putAll(students);
        users.putAll(admins);
        users.putAll(teachers);
        return users;
    }
    public User seekUser(String cedula, String clave) throws Exception {
        HashMap<String, User> users = getUsersList();
        User u;
        for (Map.Entry<String, User> set : users.entrySet()) {
            if ((u = users.get(cedula)) != null) 
                if(this.valPass(u,clave))
                    return u;
             else 
                throw new Exception("El usuario digitado no existe");
            
        }
        return null;
    }

    public boolean valPass(User u, String pass) throws Exception {
        return u.getPass().equals(pass);
    }
    
    public String showSubjects(){
        return subjects.toString();
    }
    
    public String showSubject(String id){
        return subjects.get(id).show();
    }
    
}
