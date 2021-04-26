/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.model.entities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.model.Administrator;

/**
 *
 * @author GONCAR4
 */
public class AdministratorDAO implements DAO<String, Administrator> {
    private static AdministratorDAO instance = null;
    public int getCount(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(AdministratorCRUD.CMD_COUNT)) {
                if (rs.next()) 
                    return rs.getInt("total_admins");
            } catch (SQLException ex) {
                Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
 
    public HashMap<String, Administrator> listAll() {
        HashMap<String, Administrator> u = new HashMap<>();
        String username;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(AdministratorCRUD.CMD_LIST)) {
                while (rs.next()) {
                    username = rs.getString("username");
                    u.put(username, (new Administrator(username, rs.getString("admin_id"),
                            rs.getString("email"), rs.getString("pho_num"), rs.getString("pass"))));
                }

            } catch (SQLException ex) {
                Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    public static AdministratorDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new AdministratorDAO();
        }
        return instance;
    }

    @Override
    public void add(String id, Administrator value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Administrator recover(String id, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String id, Administrator value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
