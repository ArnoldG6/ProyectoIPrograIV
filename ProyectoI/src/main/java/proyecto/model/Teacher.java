package proyecto.model;
import java.util.HashMap;

/**
 *
 * @author victo
 */
public class Teacher extends User {

    private HashMap<String, Group> groups;

    public Teacher(String username, String id, String email, String telNum, String pass) {
        this.username = username;
        this.id = id.toUpperCase(); //Ej: a00 vs A00
        this.email = email;
        this.telNum = telNum;
        this.pass = pass;
        this.groups = new HashMap<>();
        this.type = 1;
    }
    
    public Teacher() {
        this("", "", "", "", "");
    }
    public String getName(){
        return username;
    }
    public void insertGroup(Group g){
        if(groups.get(g.getNrc()) == null)
            groups.put(g.getNrc(), g);
    }
    public HashMap<String, Group> getGroups() {
         System.out.println("getGroups()");
        return groups;
    }

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

}
