/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.model.entities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.model.Administrator;

/**
 *
 * @author GONCAR4
 */
public class AdministratorDAO implements DAO<String, Administrator> {
    private BaseDatos bd;
    private static AdministratorDAO instance = null;
        private AdministratorDAO() {
        try {
            bd = BaseDatos.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Administrator> listAll() {
        List<Administrator> u = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(AdministratorCRUD.CMD_LIST)) {
            while (rs.next()) 
                   u.add(new Administrator(rs.getString("username"), rs.getString("admin_id"),
                    rs.getString("email"), rs.getString("pho_num"), rs.getString("pass")));
            //public Administrator(String name, String id, String email, String telNum, String pass) {
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return u;
        }
        return u;
    }
    public static AdministratorDAO getInstance() {
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
