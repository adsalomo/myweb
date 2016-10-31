/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschooltransverse.util;

import co.com.elenaschoolmodel.model.Configuracion;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author AdrianL
 */
public class Logging {

    /**
     * Escribe un error ocurrido por una excepción de la app
     * @param message
     * @param className
     * @param methodName
     */
    public static void writeError(String message, String className, String methodName) {
        Configuracion configuracion = Util.readFileConfiguration();
        try {
            Logger logger = Logger.getLogger("Logging ElenaSchool");
            logger.setLevel(Level.INFO);
            FileHandler handler = new FileHandler(configuracion.getLoggingFilePath(), true);
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            logger.log(Level.INFO, "Error en {0} | Metodo: {1} | Mensaje error: {2}", new Object[]{className, methodName, message + "." + "\n"});
        } catch (IOException | SecurityException ex) {
        }
    }

    /**
     * Escribe las sentencias SQL ejecutadas en la App
     * @param sql Sentencia SQL
     * @param className Nombre clase donde se ejecuta la sentencia SQL
     * @param methodName Nombre metodo donde se ejecuta la sentencia SQL
     */
    public static void writeSQL(String sql, String className, String methodName) {
        Configuracion configuracion = Util.readFileConfiguration();
        try {
            Logger logger = Logger.getLogger("Logging ElenaSchool");
            logger.setLevel(Level.INFO);
            FileHandler handler = new FileHandler(configuracion.getLoggingFilePath(), true);
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            logger.log(Level.INFO, "Sql ejecutado en {0} | Metodo: {1} | Sentencia: {2}", new Object[]{className, methodName, sql + "." + "\n"});
        } catch (IOException | SecurityException ex) {
        }
    }
}
