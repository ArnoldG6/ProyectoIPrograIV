package proyecto.model.entities;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.model.Subject;

/**
 *
 * @author GONCAR4
 */
public class SubjectDAO implements DAO<String, Subject> {

    private static SubjectDAO instance = null;
    
    public int getCount() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(SubjectCRUD.CMD_COUNT)) {
                if (rs.next()) 
                    return rs.getInt("total_subjects");
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return 0;
    }
    
    @Override
    public HashMap<String, Subject> listAll() {
        HashMap<String, Subject> u = new HashMap<>();
        String sub_name,id,desc,stat;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(SubjectCRUD.CMD_LIST)) {
                while (rs.next()) {
                    id = rs.getString("sub_id");
                    sub_name = rs.getString("sub_name");
                    desc = rs.getString("sub_desc");
                    stat = rs.getString("sub_status");
                    Subject s = (new Subject(id,sub_name,desc,stat));
                    u.put(id, s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static SubjectDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new SubjectDAO();
        }
        return instance;
    }

    @Override
    public void add(String id, Subject value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                PreparedStatement stm = cnx.prepareStatement(SubjectCRUD.CMD_ADD)) {
            stm.clearParameters();
            stm.setString(1, value.getIdSub());
            stm.setString(2, value.getNameSubj());
            stm.setString(3, value.getDesc());
            stm.setString(4, value.getStatus());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("It couldn't add the register: '%s'", id));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void add(Subject u) throws IllegalArgumentException {
        try {
            add(u.getIdSub(), u);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public Subject recover(String id, String pass) {
        pass = "";
        Subject result = null;
        String subname;
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(SubjectCRUD.CMD_RECOVER)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        subname = rs.getString("sub_name");
                        result = new Subject(subname, rs.getString("sub_id"), rs.getString("sub_desc"),rs.getString("sub_status"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
        return result;
    }
    
    @Override
    public void update(String id, Subject value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                PreparedStatement stm = cnx.prepareStatement(SubjectCRUD.CMD_UPDATE_SUB_NAME)) {
            stm.clearParameters();
            stm.setString(1, id);
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("It couldn't update the register: '%s'", value.getIdSub()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void update(Subject u) {
        update(u.getIdSub(), u);
    }

    @Override
    public void delete(String id) {
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(SubjectCRUD.CMD_DELETE)) {
                stm.clearParameters();
                stm.setString(1, id);
                if (stm.executeUpdate() != 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public Subject recover2(String id){
        String subname;
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(SubjectCRUD.CMD_RECOVER)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        subname = rs.getString("sub_name");
                        return new Subject(rs.getString("sub_id"), subname , rs.getString("sub_desc"),rs.getString("sub_status"));
                        //(String idSub, String nameSubj, String desc, String stat)
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
        return null;
    }
    
}
