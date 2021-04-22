/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.model;

/**
 *
 * @author victo
 */
public class Administrator extends User {

    public Administrator(String name, String id, String email, String telNum, String pass) {
        this.name = name;
        this.id = id.toUpperCase();
        this.email = email;
        this.telNum = telNum;
        this.pass = pass;
        this.type = 3;
    }

    public Administrator() {
        this("", "", "", "", "");
    }

    

}
