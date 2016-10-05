package co.com.elenaschooldataaccess.persistencia.dataaccess;

import co.com.elenaschooldataaccess.persistencia.helper.ModelMapperHelper;
import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.helper.ModelHelper;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschooltransverse.util.Connection;
import co.com.elenaschooltransverse.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Implementación crud dynamic table
 *
 * @author AdrianL
 */
public class ModelDao implements IModelDao {

    private final Connection conexion = Connection.getInstance();
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(conexion.getDataSource());
    
    /**
     * Obtiene la estructura de una tabla de la base de datos
     *
     * @param model objeto Model de la estructura de una tabla
     * @return
     * @throws SQLException
     */
    @Override
    public List<Model> getEstructura(Model model) throws SQLException {
        return jdbcTemplate.query(Util.SQL_ESTRUCTURA, new Object[]{model.getNameTable()}, new ModelHelper());
    }

    /**
     * Realiza consulta a una tabla
     *
     * @param query
     * @return objeto queryModel
     * @throws SQLException
     */
    @Override
    public List<Object> getConsulta(String query) throws SQLException {
        try {
            preparedStatement = conexion.getDataSource().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return ModelMapperHelper.mapRersultSetToObject(resultSet);
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

}
