package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
public class StudentCRUD {
    
    protected static final String CMD_LIST
            = "SELECT stu_id, username, email, pho_num, pass FROM students;";
    protected static final String CMD_ADD
            = "INSERT INTO students (stu_id, username, email, pho_num, pass) "
            + "VALUES (?, ?, ?, ?, ?); ";
    protected static final String CMD_RECOVER
            = "SELECT stu_id, username, email, pho_num, pass FROM students "
            + "WHERE stu_id = ?; ";
    protected static final String CMD_UPDATE_STU_PASS
            = "UPDATE students SET pass = ?"
            + "WHERE stu_id = ?; ";
    protected static final String CMD_UPDATE_STU_ID
            = "UPDATE students SET pass = ?"
            + "WHERE stu_id = ?; ";
    protected static final String CMD_DELETE
            = "DELETE FROM students "
            + "WHERE stu_id = ?; ";
    protected static final String CMD_COUNT =
            "SELECT COUNT(*) AS total_students FROM students;";
}
