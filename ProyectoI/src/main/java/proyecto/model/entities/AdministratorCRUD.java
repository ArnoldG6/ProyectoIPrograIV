package proyecto.model.entities;

public class AdministratorCRUD {
    protected static final String CMD_LIST
            = "SELECT admin_id, username, email, pho_num, pass FROM administrators "
            + "ORDER BY admin_id;";
    protected static final String CMD_ADD
            = "INSERT INTO administrators (admin_id, username, email, pho_num, pass) "
            + "VALUES (?, ?, ?, ?, ?); ";
    protected static final String CMD_RECOVER
            = "SELECT admin_id, username, email, pho_num, pass FROM administrators "
            + "WHERE admin_id = ?; ";
    protected static final String CMD_UPDATE_ADMIN_PASS
            = "UPDATE administrators SET pass = ?"
            + "WHERE admin_id = ?; ";
        protected static final String CMD_UPDATE_ADMIN_iD
            = "UPDATE administrators SET pass = ?"
            + "WHERE admin_id = ?; ";
        protected static final String CMD_DELETE
            = "DELETE FROM administrators "
            + "WHERE admin_id = ?; ";
}

