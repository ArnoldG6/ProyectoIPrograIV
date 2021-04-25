package proyecto.model.entities;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Georges Alfaro S.
 * @version 1.0
 * 
 */
public class BaseDatos extends MysqlDataSource {

    private BaseDatos() throws IOException {
        this.configuration = new Properties();
        configuration.load(getClass().getResourceAsStream(CONFIGURATION_PATH));
        setURL(String.format("%s//%s/%s",
                this.configuration.getProperty("protocol"),
                this.configuration.getProperty("server_url"),
                this.configuration.getProperty("database")

        ));
        setUser(this.configuration.getProperty("user"));
        setPassword(this.configuration.getProperty("password"));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    public static BaseDatos getInstance() throws IOException {
        if (instancia == null) {
            try {
                instancia = new BaseDatos();
                System.out.println("HOLA 1\n");
            } catch (IOException ex) { 
                System.out.println("HOLA 2\n");
                throw ex;
                
            }
        }
        System.out.println("HOLA 3\n");
        return instancia;
    }

    private static final String CONFIGURATION_PATH = "db.properties";
    private static BaseDatos instancia = null;
    private Properties configuration = null;
}
