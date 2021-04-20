
package proyecto.model;

/**
 *
 * @author victo
 */
public class User implements java.io.Serializable {

    String name;
    String id;
    String email;
    String telNum;
    String pass;
    String type;
    
    public User() {
        name = ""; 
        id = "";
        email = "";
        telNum = "";
        pass = "";
        type ="";
    }

    
    public String getType() {
        return type;
    }
    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    
    public boolean validPassword(String password){
        return false;
    }
}
