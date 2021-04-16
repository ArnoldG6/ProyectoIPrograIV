/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arnoldgq
 */
public class Model {
     private static Model instance;
     HashMap<String,Student> students;
     HashMap<String,Subject> subjects;
     HashMap<String,Teacher> teachers;
     HashMap<String,Administrator> admins;
     public Model(){
         students = new HashMap<String, Student>();
         subjects = new HashMap<String, Subject>();
         teachers = new HashMap<String, Teacher>();
         admins = new HashMap<String,Administrator>();
         
         //Some dummy objects in order to test the login and logout feature.
         students.put("1234",new Student("Juan Papu", "1234", "jp@gmail.com","555"));
         teachers.put("4321",new Teacher("Cristian Aguilar", "4321", "ca@gmail.com","665"));
         admins.put("0",new Administrator("admin", "0", "root0@gmail.com","123"));
     }
     public static Model getInstance(){
        if (instance == null)
            instance = new Model();
        return instance; 
    }
    public User seekUser(String cedula,String clave) throws Exception{
        for (Map.Entry<String, Student> set: students.entrySet()) 
            if (students.get(cedula)!=null) return students.get(cedula);
            else throw new Exception("El usuario digitado no existe");
        return null;
    }
    public boolean valPass(String cedula,String clave)  throws Exception{
     User u = seekUser(cedula, clave);
     return ((u != null) && (u.getPass().equals(clave)));
    }
}
