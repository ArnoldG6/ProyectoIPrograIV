package Model;

/**
 *
 * @author victo
 */
public class Teacher extends User {

    public Teacher(String _name, String _id, String _email, String _telephoneNumber){
        this.name=_name;
        this.id=_id;
        this.email=_email;
        this.telephoneNumber=_telephoneNumber;
    }
    
    public Teacher(){
        this("","","","");
    }
    
    @Override
    public boolean validPassword(String password) {
        return false;
    }
}
