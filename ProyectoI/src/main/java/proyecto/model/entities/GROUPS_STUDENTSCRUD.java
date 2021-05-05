/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.model.entities;

/**
 *
 * @author arnol
 */
public class GROUPS_STUDENTSCRUD {
    protected static final String CMD_LIST
            = "SELECT Groups_gro_id, Students_stu_id, grade FROM groups_has_Students;";
    protected static final String CMD_ADD
            = "INSERT INTO groups_has_Students (Groups_gro_id, Students_stu_id, grade) "
            + "VALUES (?, ?, ?); ";
    protected static final String CMD_RECOVER_BY_STUDENT
            = "SELECT Groups_gro_id, Students_stu_id, grade FROM groups_has_Students "
            + "WHERE Students_stu_id = ?; ";
        protected static final String CMD_RECOVER_BY_GROUP
            = "SELECT Groups_gro_id, Students_stu_id, grade FROM groups_has_Students "
            + "WHERE Groups_gro_id = ?; ";
    protected static final String CMD_UPDATE_GRADE
            = "UPDATE groups_has_Students SET grade = ?"
            + "WHERE Groups_gro_id = ? AND Students_stu_id = ? ; ";
    protected static final String CMD_DELETE_STU
            = "DELETE FROM groups_has_Students "
            + "WHERE Students_stu_id = ?; ";
        protected static final String CMD_DELETE_GROUP
            = "DELETE FROM groups_has_Students "
            + "WHERE Groups_gro_id = ?; ";
    protected static final String CMD_COUNT
            = "SELECT COUNT(*) AS total_groups FROM groups_has_Students;";
}