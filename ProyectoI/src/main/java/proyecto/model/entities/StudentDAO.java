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
import proyecto.model.Student;

/**
 *
 * @author GONCAR4
 */
public class StudentDAO implements DAO<String, Student> {
    
    private static StudentDAO instance = null;

    public int getCount() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(StudentCRUD.CMD_COUNT)) {
                if (rs.next()) 
                    return rs.getInt("total_students");
                
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
    public HashMap<String, Student> listAll() {
        HashMap<String, Student> u = new HashMap<>();
        String username,id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(StudentCRUD.CMD_LIST)) {
                while (rs.next()) {
                    username = rs.getString("username");
                    id = rs.getString("stu_id");
                    u.put(id, (new Student(username, id,
                            rs.getString("email"), rs.getString("pho_num"), rs.getString("pass"))));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static StudentDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    @Override
    public void add(String id, Student value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                PreparedStatement stm = cnx.prepareStatement(StudentCRUD.CMD_ADD)) {
            stm.clearParameters();
            stm.setString(1, value.getId());
            stm.setString(2, value.getName());
            stm.setString(3, value.getEmail());
            stm.setString(4, value.getTelNum());
            stm.setString(5, value.getPass());
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("It couldn't add the register: '%s'", id));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void add(Student u) throws IllegalArgumentException {
        try {
            add(u.getId(), u);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public Student recover(String id, String pass) {
        Student result = null;
        String username;
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(StudentCRUD.CMD_RECOVER)) {
                stm.clearParameters();
                stm.setString(1, id);
                stm.setString(2, pass);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("username");
                        result = new Student(username, rs.getString("stu_id"),
                                rs.getString("email"), rs.getString("pho_num"), rs.getString("pass"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
        return result;
    }

    @Override
    //This update only updates stu pass, not sure about updating stu id
    public void update(String id, Student value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                PreparedStatement stm = cnx.prepareStatement(StudentCRUD.CMD_UPDATE_STU_PASS)) {
            stm.clearParameters();
            stm.setString(1, id);
            if (stm.executeUpdate() != 1) {
                throw new IllegalArgumentException(
                        String.format("It couldn't update the register: '%s'", value.getId()));
            }
        } catch (IllegalArgumentException | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public void update(Student u) {
        update(u.getId(), u);
    }

    @Override
    public void delete(String id) {
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(StudentCRUD.CMD_DELETE)) {
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
    
}
