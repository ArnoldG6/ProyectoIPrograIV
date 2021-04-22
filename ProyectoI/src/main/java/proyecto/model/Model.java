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
    //HashMap<String, String> passwords; //Lleva el registro de todas las claves

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

    public User seekUser(String cedula, String clave) throws Exception {
        for (Map.Entry<String, Student> set : students.entrySet()) {
            if (students.get(cedula) != null) {
                return students.get(cedula);
            } else {
                throw new Exception("El usuario digitado no existe");
            }
        }
        return null;
    }

    public boolean valPass(String cedula, String clave) throws Exception {
        User u = seekUser(cedula, clave);
        return ((u != null) && (u.getPass().equals(clave)));
    }
    
    public String showSubjects(){
        return subjects.toString();
    }
    
    public String showSubject(String id){
        return subjects.get(id).show();
    }
    
    public String randomPass(){
        return "";
    }
    
    public String returnPass(String id, String nom, String em, String cllph){
        return "";
    }
    
}
