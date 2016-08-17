package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.ParametroModel;
import co.com.elenaschool.persistencia.contract.IParametroDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author aye
 * @version 1.0
 * @created 17-ago-2016 08:41:40
 */
@Component
public class ParametroDao implements IParametroDao {

    @Override
    public boolean deleteParametro(ParametroModel parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertParametro(ParametroModel parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ParametroModel> readParametro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateParametro(ParametroModel parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}//end ParametroDao
