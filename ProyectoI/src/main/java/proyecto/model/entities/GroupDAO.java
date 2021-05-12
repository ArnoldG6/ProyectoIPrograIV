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
import static proyecto.model.entities.SubjectDAO.recover2;
import static proyecto.model.entities.TeacherDAO.recover4;

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
                    u.put(id, (new Group(id, recover2(rs.getString("subject_id")), recover4(rs.getString("teachers_tea_id")), Integer.parseInt(rs.getString("num_stu")))));
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
            stm.setString(1, value.getNrc());
            stm.setString(2, value.getTeacher().getIdSub());
            stm.setString(3, Integer.toString(value.getNumStu()));
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
            }
        } catch (SQLException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
        return result;
    }

    public Group recover3(String id) {
        Group result = null;
        String username;
        try {
            try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?useSSL=false", "root", "root");
                    PreparedStatement stm = cnx.prepareStatement(GroupCRUD.CMD_RECOVER)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("gro_id");
                        result = new Group(id, recover2(rs.getString("subject_id")), recover4(rs.getString("teachers_tea_id")), Integer.parseInt(rs.getString("num_stu")));
                    }
                }
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

    public static GroupDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new GroupDAO();
        }
        return instance;
    }

    private Teacher searchTeacher(String string) throws Exception {
        return Model.getInstance().searchTeacher(string);
    }

}
