package co.com.elenaschooltransverse.util;

import co.com.elenaschoolmodel.model.ConexionModel;
import co.com.elenaschoolmodel.model.Configuracion;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * Obtiene datasource para la conexión a la base de datos
     *
     * @return DataSource
     */
    public static DataSource getDataSource() {
        // Lee archivo configuracion
        Configuracion configuracion = readFileConfiguration();

        // Asigna la ruta
        String ruta = configuracion.getConnectionFilePath();

        List<ConexionModel> list = null;
        list = (List<ConexionModel>) deserialize(ruta);
        ConexionModel conexionModel = new ConexionModel();

        if (list != null && list.size() > 0) {
            conexionModel = list.get(0);
        }

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
     * Lee archivo configuración con los parametros de la aplicacion
     *
     * @return
     */
    public static Configuracion readFileConfiguration() {
        Configuracion configuracion = null;
        InputStream resp = Util.class.getClassLoader().getResourceAsStream("co/com/elenaschooltransverse/resource/Configuracion.xml");
        configuracion = (Configuracion) deserialize(resp);
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
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
            obj = decoder.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
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
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            encoder.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
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
            + "ccu.table_name AS foreignTableName "
            + "FROM information_schema.columns c "
            + "LEFT JOIN information_schema.key_column_usage k ON c.table_name = k.table_name AND c.column_name = k.column_name "
            + "LEFT JOIN information_schema.table_constraints tc ON K.constraint_name = tc.constraint_name AND tc.constraint_type = 'FOREIGN KEY' "
            + "LEFT JOIN information_schema.constraint_column_usage ccu ON ccu.constraint_name = tc.constraint_name "
            + "WHERE "
            + "c.table_schema LIKE 'public' "
            + "AND c.table_catalog LIKE 'elenaschool' "
            + "AND c.table_name LIKE ? ";

}
