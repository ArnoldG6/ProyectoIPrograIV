package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
public class TeacherCRUD {
    
    protected static final String CMD_LIST
            = "SELECT tea_id, username, email, phone_num, pass FROM teachers;";
    protected static final String CMD_ADD
            = "INSERT INTO teachers (tea_id, username, email, phone_num, pass) "
            + "VALUES (?, ?, ?, ?, ?); ";
    protected static final String CMD_RECOVER
            = "SELECT stu_id, username, email, phone_num, pass FROM teachers "
            + "WHERE stu_id = ?; ";
    protected static final String CMD_UPDATE_TEA_PASS
            = "UPDATE teachers SET pass = ?"
            + "WHERE tea_id = ?; ";
    protected static final String CMD_UPDATE_TEA_ID
            = "UPDATE teachers SET pass = ?"
            + "WHERE tea_id = ?; ";
    protected static final String CMD_DELETE
            = "DELETE FROM teachers "
            + "WHERE tea_id = ?; ";
    protected static final String CMD_COUNT =
            "SELECT COUNT(*) AS total_teachers FROM teachers;";
}
