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

    public Administrator(String name, String id, String email, String telNum, String pas) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telNum = telNum;
        this.pass = pas;
        this.type = "ADM";
    }

    public Administrator() {
        this("", "", "", "", "");
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }
}
