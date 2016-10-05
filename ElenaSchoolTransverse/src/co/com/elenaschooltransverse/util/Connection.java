package co.com.elenaschooltransverse.util;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Clase singleton para obtener conexión
 *
 * @author AdrianL
 */
public class Connection {

    private static DataSource dataSource;
    private static Connection instance;

    /**
     * constructor
     */
    private Connection() {
        setDataSource();
    }

    /**
     * Instancia la clase conexión
     *
     * @return
     */
    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
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
}
