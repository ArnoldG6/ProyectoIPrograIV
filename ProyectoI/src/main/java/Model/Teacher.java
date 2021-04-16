package Model;

/**
 *
 * @author victo
 */
public class Teacher extends User {

    public Teacher(){
        this.name="";
        this.id="";
        this.email="";
        this.telephoneNumber="";
    }
    
    public Teacher(String _name, String _id, String _email, String _telephoneNumber){
        this.name=_name;
        this.id=_id;
        this.email=_email;
        this.telephoneNumber=_telephoneNumber;
    }
    
    @Override
    public void validPassword(String password) {
        
    }
}
