package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.ClaseModel;
import co.com.elenaschool.persistencia.contract.IClaseDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author aye
 * @version 1.0
 * @created 17-ago-2016 10:30:25
 */
@Component
public class ClaseDao implements IClaseDao {

    @Override
    public boolean deleteClase(ClaseModel clase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertClase(ClaseModel clase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ClaseModel> readClase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateClase(ClaseModel clase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
    
}//end ClaseDao
