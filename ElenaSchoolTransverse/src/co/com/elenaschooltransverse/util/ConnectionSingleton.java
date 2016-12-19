package co.com.elenaschooltransverse.util;

import java.sql.Connection;
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
    private static Connection connection;

    /**
     * constructor
     */
    private ConnectionSingleton() throws SQLException {
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
        }
        return instance;
    }
    
    /**
     * setea el datasource
     */
    private void setDataSource() {
        dataSource = Util.getDataSource();
    }

    /**
     * Asigna el objeto datasource
     */
    private void setConnection() throws SQLException{
        connection = dataSource.getConnection();
    }

    /**
     *Obtiene objeto connection
     *
     * @return dataSource
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException{
        setConnection();
        return connection;
    }

    /**
     * Cierra la conexion
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
