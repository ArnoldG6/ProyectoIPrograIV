package proyecto.model.entities;

public class AdministratorsCRUD {
    protected static final String CMD_LIST
            = "SELECT admin_id, username, email, pho_num, password FROM administrators "
            + "ORDER BY admin_id;";
    protected static final String CMD_ADD
            = "INSERT INTO administrators (admin_id, username, email, pho_num, password) "
            + "VALUES (?, ?, ?, ?, ?); ";
    protected static final String CMD_RECOVER
            = "SELECT admin_id, username, email, pho_num, password FROM administrators "
            + "WHERE admin_id = ?; ";
    protected static final String CMD_UPDATE_ADMIN_PASS
            = "UPDATE administrators SET password = ?"
            + "WHERE admin_id = ?; ";
        protected static final String CMD_UPDATE_ADMIN_iD
            = "UPDATE administrators SET password = ?"
            + "WHERE admin_id = ?; ";
        protected static final String CMD_DELETE
            = "DELETE FROM administrators "
            + "WHERE admin_id = ?; ";
}

