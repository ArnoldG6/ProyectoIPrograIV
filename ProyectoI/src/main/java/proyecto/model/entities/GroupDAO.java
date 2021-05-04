package proyecto.model.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.model.Group;
import proyecto.model.Model;
import proyecto.model.Teacher;

/**
 *
 * @author GONCAR4
 */
public class GroupDAO implements DAO<String, Group> {
    
    private static GroupDAO instance = null;
    
    @Override
    public HashMap<String, Group> listAll() {
        HashMap<String, Group> u = new HashMap<>();
        String id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(GroupCRUD.CMD_LIST)) {
                while (rs.next()) {
                    id = rs.getString("gro_id");
                    u.put(id, (new Group( id, searchTeacher(rs.getString("teachers_gro_id")), Integer.parseInt(rs.getString("num_stu")))));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    //We have to see how add due to the relationships
    public void add(String id, Group value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                PreparedStatement stm = cnx.prepareStatement(GroupCRUD.CMD_ADD)) {
            stm.clearParameters();
//            stm.setString(1, value.getNrc());
//            stm.setString(2, value.);
//            stm.setString(3, value.getEmail());
//            stm.setString(4, value.getTelNum());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("It couldn't add the register: '%s'", id));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void add(Group u) throws IllegalArgumentException {
        try {
            add(u.getNrc(), u);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public Group recover(String id, String pass) {
        Group result = null;
        String username;
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(GroupCRUD.CMD_RECOVER)) {
                stm.clearParameters();
                stm.setString(1, id);
                stm.setString(2, pass);
//                try (ResultSet rs = stm.executeQuery()) {
//                    if (rs.next()) {
//                        username = rs.getString("username");
//                        result = new Student(username, rs.getString("stu_id"),
//                                rs.getString("email"), rs.getString("pho_num"), rs.getString("pass"));
//                    }
//                }
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
        return result;
    }

    @Override
    public void update(String id, Group value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                PreparedStatement stm = cnx.prepareStatement(GroupCRUD.CMD_UPDATE_NUM_STU)) {
            stm.clearParameters();
            stm.setString(1, id);
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("It couldn't update the register: '%s'", value.getNrc()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void update(Group u) {
        update(u.getNrc(), u);
    }

    @Override
    public void delete(String id) {
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(GroupCRUD.CMD_DELETE)) {
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

    private Teacher searchTeacher(String string) {
        return Model.getInstance().searchTeacher(string);
    }
    
}
