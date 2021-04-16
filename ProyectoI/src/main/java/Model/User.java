
package Model;

import java.util.List;

/**
 *
 * @author victo
 */
public abstract class User {

    public String name;
    public String id;
    public String email;
    public String telephoneNumber;
    
    public abstract boolean validPassword(String password);
}
