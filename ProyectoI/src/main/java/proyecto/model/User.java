package proyecto.model;

/**
 *
 * @author victo
 */
public class User implements java.io.Serializable {

    String username;
    String id;
    String email;
    String telNum;
    String pass;
    int type;

    public User() {
        username="";
        id="";
        email="";
        telNum="";
        pass="";
        type=0;

    }

    public int getType() {
        return type;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void telNum(String telNum) {
        this.telNum = telNum;
    }

}
