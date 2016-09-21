package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.ParametroModel;
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
    public boolean deleteParametro(ParametroModel parametro);

    /**
     *
     * @param parametro
     */
    public boolean insertParametro(ParametroModel parametro);

    /**
     *
     * @return @throws SQLException
     */
    public List<ParametroModel> readParametro();

    /**
     *
     * @param parametro
     */
    public boolean updateParametro(ParametroModel parametro);

}
