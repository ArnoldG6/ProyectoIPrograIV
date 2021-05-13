package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
public class GroupCRUD {
     protected static final String CMD_LIST
            = "SELECT gro_id, subject_id, num_stu, teachers_tea_id FROM groups;";
    protected static final String CMD_ADD
            = "INSERT INTO university.groups (gro_id, subject_id, num_stu, teachers_tea_id) "
            + "VALUES (?, ?, ?, ?); ";
    protected static final String CMD_RECOVER
            = "SELECT gro_id, subject_id, num_stu, teachers_tea_id FROM groups "
            + "WHERE gro_id = ?; ";
    protected static final String CMD_UPDATE_NUM_STU
            = "UPDATE groups SET num_stu = ?"
            + "WHERE gro_id = ?; ";
    protected static final String CMD_DELETE
            = "DELETE FROM groups "
            + "WHERE gro_id = ?; ";
    protected static final String CMD_COUNT =
             "SELECT COUNT(*) AS total_groups FROM groups;";
}
