package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.ParametroModel;
import co.com.elenaschool.persistencia.contract.IParametroDao;
import java.util.List;

/**
 * @author aye
 * @version 1.0
 * @created 17-ago-2016 08:41:40
 */
public class ParametroDao implements IParametroDao {

    public ParametroDao() {

    }

    /**
     *
     * @param parametro
     */
    public boolean deleteParametro(ParametroModel parametro) {
        return false;
    }

    /**
     *
     * @param parametro
     */
    public boolean insertParametro(ParametroModel parametro) {
        return false;
    }

    /**
     * 
     * @return 
     */
    public List<ParametroModel> readParametro() {
        return null;
    }

    /**
     *
     * @param parametro
     */
    public boolean updateParametro(ParametroModel parametro) {
        return false;
    }
}//end ParametroDao
