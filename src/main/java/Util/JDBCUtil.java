package Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Close {

    /**
     * Safely closes a ResultSet and PreparedStatement, ignoring nulls.
     * Does NOT close the Connection, since you're using a singleton.
     */
    public static void closeResources(ResultSet rs, PreparedStatement ps) {
        if (rs != null) {
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        if (ps != null) {
            try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    /**
     * Overload for just closing a PreparedStatement
     */
    public static void closeResources(PreparedStatement ps) {
        closeResources(null, ps);
    }
}