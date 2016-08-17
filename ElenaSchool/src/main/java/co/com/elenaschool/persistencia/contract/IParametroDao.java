package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.ParametroModel;
import java.sql.SQLException;
import java.util.List;

/**
 * @author aye
 * @version 1.0
 * @created 17-ago-2016 08:38:35
 */
public interface IParametroDao {

    /**
     *
     * @param parametro
     */
    public boolean deleteParametro(ParametroModel parametro) throws SQLException;

    /**
     *
     * @param parametro
     */
    public boolean insertParametro(ParametroModel parametro) throws SQLException;

    /**
     *
     * @return @throws SQLException
     */
    public List<ParametroModel> readParametro() throws SQLException;

    /**
     *
     * @param parametro
     */
    public boolean updateParametro(ParametroModel parametro) throws SQLException;

}
