package Model;

/**
 *
 * @author victo
 */
public class Student extends User {

    public Student(String _name, String _id, String _email, String _telephoneNumber){
        this.name=_name;
        this.id=_id;
        this.email=_email;
        this.telephoneNumber=_telephoneNumber;
    }
    
    public Student(){
        this("","","","");
    }
    
    @Override
    public boolean validPassword(String password) {
        return false;
    }
    
}
