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

    public Administrator(String na, String ID, String e, String telNum) {
        this.name = na;
        this.id = ID;
        this.email = e;
        this.telephoneNumber = telNum;
    }

    public Administrator() {
        this("", "", "", "");
    }

    @Override
    public boolean validPassword(String password) {
        return false;
    }
}
