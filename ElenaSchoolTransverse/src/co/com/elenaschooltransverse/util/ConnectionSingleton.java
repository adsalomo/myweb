package co.com.elenaschooltransverse.util;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Clase singleton para obtener conexión
 *
 * @author AdrianL
 */
public class ConnectionSingleton {

    private static DataSource dataSource;
    private static ConnectionSingleton instance;

    /**
     * constructor
     */
    private ConnectionSingleton() {
        setDataSource();
    }

    /**
     * Instancia la clase conexión
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static ConnectionSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionSingleton();
        } else if(instance.getDataSource().getConnection().isClosed()){
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    /**
     * Asigna el objeto datasource
     */
    private void setDataSource() {
        dataSource = Util.getDataSource();
    }

    /**
     * Obtiene el objeto datasource
     *
     * @return dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }
    
    /**
     * Cierra la conexion
     * @throws SQLException 
     */
    public void CloseConnection() throws SQLException{
        if(instance != null && instance.getDataSource() != null && !instance.getDataSource().getConnection().isClosed())
            instance.getDataSource().getConnection().close();
    }
}
