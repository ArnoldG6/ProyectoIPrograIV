package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
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
    protected static final String CMD_UPDATE
            //Hay que ver que se va a actualizar
            = "UPDATE administrators SET contra = ?"
            + "WHERE idUsuario = ?; ";
        protected static final String CMD_DELETE
            = "DELETE FROM administrators "
            + "WHERE admin_id = ?; ";
}

