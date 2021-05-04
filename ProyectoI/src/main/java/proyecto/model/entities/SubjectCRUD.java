package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
public class SubjectCRUD {

    protected static final String CMD_LIST
            = "SELECT sub_id, sub_name, sub_desc sub_statu FROM subjects;";
    protected static final String CMD_ADD
            = "INSERT INTO subjects (sub_id, sub_name, sub_desc, sub_statu) "
            + "VALUES (?, ?, ?); ";
    protected static final String CMD_RECOVER
            = "SELECT sub_id, sub_name, sub_desc, sub_statu FROM subjects "
            + "WHERE sub_id = ?; ";
    protected static final String CMD_UPDATE_SUB_NAME
            = "UPDATE subjects SET sub_name = ?"
            + "WHERE sub_id = ?; ";
    protected static final String CMD_DELETE
            = "DELETE FROM subjects "
            + "WHERE sub_id = ?; ";
    protected static final String CMD_COUNT
            = "SELECT COUNT(*) AS total_subjects FROM subjects;";
}
