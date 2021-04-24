/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.model.entities;

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
public class AdministratorsDAO implements DAO<String, Administrator> {

    /*
    private static final String CMD_ESTABLECER_CONECTADO
            = "UPDATE Usuario SET conectado = TRUE"
            + " WHERE idUsuario = ?; ";
    private static final String CMD_ESTABLECER_DESCONECTADO
            = "UPDATE Usuario SET conectado = FALSE"
            + " WHERE idUsuario = ?; ";
    private static final String CMD_CUENTA_REGISTROS
            = "SELECT COUNT(*) AS cuenta FROM Usuario; ";
     */
    private BaseDatos bd;
//String name, String id, String email, String telNum, String pass

    @Override
    public List<Administrator> listAll() {
        List<Administrator> u = new ArrayList<>();
        try (Connection cnx = bd.getConnection();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(AdministratorsCRUD.CMD_LIST)) {
            while (rs.next()) {
                    Administrator us
                    = new Administrator(rs.getString("admin_id"), rs.getString("username"),
                    rs.getString("email"), rs.getString("pho_num"), rs.getString("password"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdministratorsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
        return u;
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
