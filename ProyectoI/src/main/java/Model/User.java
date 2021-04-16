
package Model;

/**
 *
 * @author victo
 */
public abstract class User implements java.io.Serializable {

    public String name;
    public String id;
    public String email;
    public String telephoneNumber;
    
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    
    public abstract boolean validPassword(String password);
}
