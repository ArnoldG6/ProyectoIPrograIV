package proyecto.model.entities;

//import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//public class DataBases extends MysqlDataSource {
//    private DataBases() throws IOException {
//        this.configuration = new Properties();
//        configuration.load(getClass().getResourceAsStream(CONFIGURATION_PATH));
//
//        setURL(String.format("%s//%s/%s",
//                this.configuration.getProperty("protocol"),
//                this.configuration.getProperty("server_url"),
//                this.configuration.getProperty("database")
//        // jdbc:mysql://localhost:3306/modelo_chat
//
//        ));
//        setUser(this.configuration.getProperty("user"));
//        setPassword(this.configuration.getProperty("password"));
//    }
//
//    @Override
//    public Connection getConnection() throws SQLException {
//        return super.getConnection();
//    }
//
//    public static DataBases obtenerInstancia() throws IOException {
//        if (instance == null) {
//            try {
//                instance = new DataBases();
//            } catch (IOException ex) {
//                System.err.printf("Excepción: '%s'%n", ex.getMessage());
//                throw ex;
//            }
//        }
//        return instance;
//    }
//
//    private static final String CONFIGURATION_PATH = "db.properties";
//    private static DataBases instance = null;
//    private Properties configuration = null;
//}
