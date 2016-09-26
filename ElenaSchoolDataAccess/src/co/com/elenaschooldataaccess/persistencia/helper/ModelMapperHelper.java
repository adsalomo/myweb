package co.com.elenaschooldataaccess.persistencia.helper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AdrianL
 */
public class ModelMapperHelper{

    /**
     * Mapea el valor a la propiedad del objeto que venga por parametro
     * @param rs Objeto resultSet obtenido de la ejecución de la consulta
     * @return
     * @throws SQLException
     */
    public static List<Object> mapRersultSetToObject(ResultSet rs) throws SQLException {
        List<Object> outputList = new ArrayList<>();
        if (rs != null) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>(); 
                for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                    String columnName = rsmd.getColumnName(_iterator + 1);
                    Object columnValue = rs.getObject(_iterator + 1);
                    map.put(columnName, columnValue);
                }
                outputList.add(map);
            }
        }
        return outputList;
    }
}