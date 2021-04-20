package proyecto.model.entities;

/**
 *
 * @author GONCAR4
 */
public class StudentsCRUD {

    protected static final String CMD_LIST
            = "SELECT stu_id, username, email, pho_num FROM Students "
            + "ORDER BY stu_id;";
    protected static final String CMD_ADD
            = "INSERT INTO Students (stu_id, username, email, pho_num) "
            + "VALUES (?, ?, ?, ?); ";

    protected static final String CMD_RECOVER
            = "SELECT stu_id, username, email, pho_num FROM Usuario "
            + "WHERE idUsuario = ?; ";
    protected static final String CMD_UPDATE
            = "UPDATE Usuario SET contra = ?"
            + "WHERE idUsuario = ?; ";
    protected static final String CMD_DELETE
            = "DELETE FROM Usuario "
            + "WHERE idUsario = ?; ";
}
