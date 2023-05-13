
package cr.ac.una.tecsolve.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Dolmus
 */
public class BaseData {
    private final static String USER = "root";
    private final static String PASSW = "";
    private final static String DATA = "bdtecsolve";
    private final static String SERVER = "localhost";
    private final static String URL = "jdbc:mysql://" + SERVER + ":3306/" + DATA;
    private Connection conexion;

    protected Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (conexion == null) {
                conexion = DriverManager.getConnection(URL, USER, PASSW);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexion;
    }
}
