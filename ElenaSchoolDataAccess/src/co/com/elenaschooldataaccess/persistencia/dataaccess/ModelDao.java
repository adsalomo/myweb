package co.com.elenaschooldataaccess.persistencia.dataaccess;

import co.com.elenaschooldataaccess.persistencia.helper.ModelMapperHelper;
import co.com.elenaschooldataaccess.persistencia.contract.IModelDao;
import co.com.elenaschooldataaccess.persistencia.helper.ModelHelper;
import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschooltransverse.util.ConnectionSingleton;
import co.com.elenaschooltransverse.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementación crud dynamic table
 *
 * @author AdrianL
 */
public class ModelDao implements IModelDao {

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private ConnectionSingleton conexion;
    
    /**
     * Obtiene la estructura de una tabla de la base de datos
     * @param model objeto Model de la estructura de una tabla
     * @return
     * @throws SQLException
     */
    @Override
    public List<Model> getEstructura(Model model) throws SQLException {
        conexion = ConnectionSingleton.getInstance();
        try{
            preparedStatement = conexion.getDataSource().getConnection().prepareStatement(Util.SQL_ESTRUCTURA);
            preparedStatement.setString(1, model.getNameTable());
            resultSet = preparedStatement.executeQuery();
            return ModelHelper.getEstructura(resultSet);
        } finally {
            preparedStatement.close();
            resultSet.close();
            conexion.getDataSource().getConnection().close();
        }
    }

    /**
     * Realiza consulta a una tabla
     * @param query
     * @return objeto queryModel
     * @throws SQLException
     */
    @Override
    public List<Object> getConsulta(String query) throws SQLException {
        conexion = ConnectionSingleton.getInstance();
        try {
            preparedStatement = conexion.getDataSource().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return ModelMapperHelper.mapRersultSetToObject(resultSet);
        } finally {
            preparedStatement.close();
            resultSet.close();
            conexion.getDataSource().getConnection().close();
        }
    }

}
