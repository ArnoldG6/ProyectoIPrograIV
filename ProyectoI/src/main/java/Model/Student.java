package Model;

/**
 *
 * @author victo
 */
public class Student extends User {

    public Student(){
        this.name="";
        this.id="";
        this.email="";
        this.telephoneNumber="";
    }
    
    public Student(String _name, String _id, String _email, String _telephoneNumber){
        this.name=_name;
        this.id=_id;
        this.email=_email;
        this.telephoneNumber=_telephoneNumber;
    }
    
    @Override
    public void validPassword(String password) {
        
    }
    
}
