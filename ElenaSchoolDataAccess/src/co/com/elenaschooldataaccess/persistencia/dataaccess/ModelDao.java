package co.com.elenaschooldataaccess.persistencia.dataaccess;

import co.com.elenaschooldataaccess.persistencia.helper.DynamicMapperHelper;
import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.helper.ModelHelper;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschooltransverse.util.Util;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Implementación crud dynamic table
 *
 * @author AdrianL
 */
public class ModelDao implements IModelDao {

    /**
     * Obtiene la estructura de una tabla de la base de datos
     * @param model objeto Model de la estructura de una tabla
     * @return
     * @throws SQLException
     */
    @Override
    public List<Model> getEstructura(Model model) throws SQLException {
       JdbcTemplate jdbcTemplate = new JdbcTemplate(Util.getDataSource());
       return jdbcTemplate.query(Util.SQL_ESTRUCTURA, new Object[]{model.getNameTable()}, new ModelHelper());
    }

    /**
     * Realiza consulta a una tabla X
     * @param query
     * @return objeto queryModel
     * @throws SQLException
     */
    @Override
    public List<Object> getConsulta(String query) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Util.getDataSource());
        return jdbcTemplate.query(query, new DynamicMapperHelper());
    }

    /**
     * Realiza operacion de actualizacion de una tabla
     * @param query
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateModel(String query) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Util.getDataSource());
        jdbcTemplate.update(query);
        return true;
    }

    /**
     * Obtiene el maximo entero de una tabla
     * @param query
     * @return int id de la tabla referenciada en la consulta
     * @throws SQLException 
     */
    @Override
    public int getMaxTable(String query) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Util.getDataSource());
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    /**
     * Obtiene el codigo maximo de la tabla
     * @param query
     * @return String codigo de la tabla referenciada en la consulta
     * @throws SQLException 
     */
    @Override
    public String getMaxCodeTable(String query) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Util.getDataSource());
        return jdbcTemplate.queryForObject(query, String.class);
    }
}
