/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author victo
 */
public class Administrator extends User {

    public Administrator(){
        this.name="";
        this.id="";
        this.email="";
        this.telephoneNumber="";
    }
    
    public Administrator(String _name, String _id, String _email, String _telephoneNumber){
        this.name=_name;
        this.id=_id;
        this.email=_email;
        this.telephoneNumber=_telephoneNumber;
    }
    
    @Override
    public void validPassword(String password) {
        
    }
}
