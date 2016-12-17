package co.com.elenaschooltransverse.util;

import co.com.elenaschoolmodel.model.ActionResponse;
import co.com.elenaschoolmodel.model.ConexionModel;
import co.com.elenaschoolmodel.model.Configuration;
import co.com.elenaschoolmodel.model.ErrorRequest;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Clase estatica para agregar utilidades que son tranversales en todo el
 * proyecto
 *
 * @author AdrianL
 * @Created 21-sep-2016 08:38:26
 */
public class Util {

    private static StackTraceElement[] stackTrace;

    /**
     * Cierra el objeto preparedStatement
     * @param statement
     * @throws SQLException 
     */
    public static void closePreparedStatement(PreparedStatement statement) throws SQLException{
        if(statement != null)
            statement.close();
    }
    
    /**
     * Cierra el objeto resultSet
     * @param resultSet
     * @throws SQLException 
     */
    public static void closeResultSet(ResultSet resultSet) throws SQLException{
        if(resultSet != null)
            resultSet.close();
    }
    
    /**
     * Obtiene datasource para la conexión a la base de datos
     * @return DataSource
     */
    public static DataSource getDataSource() {
        // Lee archivo configuracion
        Configuration configuracion = readFileConfiguration();
        // Asigna la ruta
        String ruta = configuracion.getConnectionFilePath();
        // Lee el archivo conexion
        ConexionModel conexionModel = (ConexionModel)deserialize(ruta);
        // Crea el datasource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        if (conexionModel != null) {
            dataSource.setDriverClassName(conexionModel.getDriverClassName());
            dataSource.setUrl(conexionModel.getUrl());
            dataSource.setUsername(conexionModel.getUserName());
            dataSource.setPassword(conexionModel.getPassword());
        }
        return dataSource;
    }
    
    /**
     * Obtiene el error
     * @param message
     * @param errorCode
     * @return 
     */
    public static ActionResponse getError(String message, int errorCode){
        ActionResponse actionResponse = new ActionResponse();
        ErrorRequest error = new ErrorRequest();
        error.setMessage(message);
        error.setErrorCode(errorCode);
        actionResponse.setStatus(false);
        actionResponse.setError(error);
        return actionResponse;
    }

    /**
     * Lee archivo configuración con los parametros de la aplicacion
     *
     * @return
     */
    public static Configuration readFileConfiguration() {
        Configuration configuracion = null;
        InputStream resp = Util.class.getClassLoader().getResourceAsStream("co/com/elenaschooltransverse/resource/Configuracion.xml");
        configuracion = (Configuration) deserialize(resp);
        return configuracion;
    }

    /**
     * Deserializa XML
     *
     * @param fileName Nombre archivo XML
     * @return objeto leido del archivo XML
     */
    public static Object deserialize(String fileName) {
        Object obj = null;
        stackTrace = Thread.currentThread().getStackTrace();
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
            obj = decoder.readObject();
        } catch (FileNotFoundException ex) {
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
        return obj;
    }

    /**
     * Deserializa XML
     *
     * @param fileName Object input stream
     * @return
     */
    public static Object deserialize(InputStream fileName) {
        Object obj = null;
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fileName));
        obj = decoder.readObject();
        return obj;
    }

    /**
     * Serializa un objeto a XML
     *
     * @param obj Objeto a serializar
     * @param fileName Nombre archivo XML
     */
    public static void serialize(Object obj, String fileName) {
        stackTrace = Thread.currentThread().getStackTrace();
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            encoder.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logging.writeError(ex.getMessage(), ex.getStackTrace()[1].getLineNumber(), ex.getStackTrace()[1].getClassName(), ex.getStackTrace()[1].getMethodName());
        }
    }

    /**
     * Sql estructura tabla
     */
    public static final String SQL_ESTRUCTURA = "SELECT "
            + "c.table_name NameTable, "
            + "c.column_name ColumnName, "
            + "(CASE WHEN substring(k.constraint_name FROM 1 FOR 2) LIKE 'pk' THEN '1'::bit ELSE '0'::bit END) AS IsPrimary, "
            + "(CASE WHEN substring(k.constraint_name FROM 1 FOR 2) LIKE 'fk' THEN '1'::bit ELSE '0'::bit END) AS IsForeign, "
            + "c.data_type DataType, "
            + "(CASE WHEN c.is_nullable LIKE 'NO' THEN '1'::bit ELSE '0'::bit END) AS IsNullable, "
            + "c.character_maximum_length Length, "
            + "c.numeric_precision_radix NumericPrecision, "
            + "("
            + "SELECT "
            + "pg_catalog.col_description(pg.oid, c.ordinal_position::int) "
            + "FROM pg_catalog.pg_class pg "
            + "WHERE "
            + "pg.oid = (SELECT c.table_name::regclass::oid) AND "
            + "pg.relname = c.table_name "
            + ") AS LabelName, "
            + "ccu.table_name AS foreignTableName, "
            + "(CASE WHEN c.column_default != '' THEN '1'::bit ELSE '0'::bit END) AS IsSecuence "
            + "FROM information_schema.columns c "
            + "LEFT JOIN information_schema.key_column_usage k ON c.table_name = k.table_name AND c.column_name = k.column_name "
            + "LEFT JOIN information_schema.table_constraints tc ON K.constraint_name = tc.constraint_name AND tc.constraint_type = 'FOREIGN KEY' "
            + "LEFT JOIN information_schema.constraint_column_usage ccu ON ccu.constraint_name = tc.constraint_name "
            + "WHERE "
            + "c.table_schema LIKE 'public' "
            + "AND c.table_catalog LIKE 'elenaschool' "
            + "AND c.table_name LIKE ? ";

}
