package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
public class GroupsCRUD {
    protected static final String CMD_LIST
            = "SELECT gro_id FROM groups "
            + "ORDER BY admin_id;";
    protected static final String CMD_ADD
            = "INSERT INTO groups (gro_id) "
            + "VALUES (?); ";
    protected static final String CMD_RECOVER
            = "SELECT * FROM groups "
            + "WHERE gro_id = ?; ";
    protected static final String CMD_UPDATE
            //Hay que ver que se va a actualizar
            = "UPDATE groups SET gro_id = ?"
            + "WHERE gro_id = ?; ";
        protected static final String CMD_DELETE
            = "DELETE FROM groups "
            + "WHERE gro_id = ?; ";
}
