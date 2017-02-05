/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.adsalomo.transverse.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;




/**
 *
 * @author aye
 */ 
public class Util {
    
    /**
     * Obtiene la configuracion a la fuente de datos
     * @return 
     */
    public static DriverManagerDataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setPassword("adrian06");
        dataSource.setUsername("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/persona");
        return dataSource;
    }
    
    /**
     * Persiste el error en archivo .log
     * @param message
     * @param method 
     */
    public static void writeError(String message, String method) {
        try {
            FileHandler handler = new FileHandler("C:\\Notacion\\error.log", true);
            Logger logger = Logger.getLogger("Información Error");
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.log(Level.WARNING, "Error en {0} Mensaje error: {1}", new Object[]{method, message + "\n"});
            handler.close();
        } catch (SecurityException | IOException ex) {
            throw new SecurityException("Error al escribir en log del sistema " + ex.getMessage());
        }
    }
}
