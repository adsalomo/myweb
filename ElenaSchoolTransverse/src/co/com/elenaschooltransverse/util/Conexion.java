package co.com.elenaschooltransverse.util;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Clase singleton para obtener conexión
 * @author AdrianL
 */
public class Conexion {
    private static DataSource dataSource;
    private static Conexion instance;
    
    /**
     * constructor
     */
    private Conexion(){
        setDataSource();
    }
    
    /**
     * Instancia la clase conexión
     * @return 
     */
    public static Conexion getInstance(){
        if(instance == null){
            instance = new Conexion();
        }
        return instance;
    }
    
    /**
     * Asigna el objeto datasource
     */
    private void setDataSource(){
        try {
            dataSource = Util.getDataSource();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtiene el objeto datasource
     * @return dataSource
     */
    public DataSource getDataSource(){
        return dataSource;
    }
}
