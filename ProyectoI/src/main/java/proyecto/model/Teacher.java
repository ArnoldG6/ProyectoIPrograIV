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
        this.groups = new HashMap<String, Group>();
        this.type = 1;
    }

    public Teacher() {
        this("", "", "", "", "");
    }
    @Override
    public String toString() {
        String f = "";
        f = f
                + "\n" + "Grupos:"
                + "\n";
        for (int i = 0; i < getGroups().size(); i++) {
            f += getGroups().get(i).show() + "\n";
        }
        return f;
    }
    public void insertGroup(Group g){
        if(groups.get(g.getNrc()) != null)
            groups.put(g.getNrc(), g);
    }
    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

}
