/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.model.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import static jdk.nashorn.internal.objects.Global.instance;
import proyecto.model.Student;

/**
 *
 * @author arnol
 */
public class GROUPS_STUDENTSDAO  {
    static GROUPS_STUDENTSDAO instance = null;
    public int getCount() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(StudentCRUD.CMD_COUNT)) {
                if (rs.next()) 
                    return rs.getInt("total_groups");
                
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

    public ArrayList<String[]> listAll() {
         //the order of the pair is: (stuId,(groupId,grade))
        ArrayList<String[]> result = new ArrayList<String[]>();
        String[] value = new String[3];
        int grade;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    Statement stm = cnx.createStatement();
                    ResultSet rs = stm.executeQuery(GROUPS_STUDENTSCRUD.CMD_LIST)) {
                    
                while (rs.next()) {
                    value[0] = rs.getString("Groups_gro_id");
                    value[1] = rs.getString("Students_stu_id");
                    value[2] = String.valueOf(rs.getInt("grade"));
                    result.add(value);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static GROUPS_STUDENTSDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new GROUPS_STUDENTSDAO();
        }
        return instance;
    }


    public void add(String id, Student value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
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


    public Student recover(String id, String pass) {
        Student result = null;
        String username;
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
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

    //This update only updates stu pass, not sure about updating stu id
    public void update(String id, Student value) {
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
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

    public void delete(String id) {
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
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