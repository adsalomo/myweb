package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.AsignaturaModel;
import co.com.elenaschool.persistencia.contract.IAsignaturaDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:32
 */
@Component
public class AsignaturaDao implements IAsignaturaDao{

    @Override
    public boolean deleteAsignatura(AsignaturaModel asignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertAsignatura(AsignaturaModel asignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AsignaturaModel> readAsignatura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateAsignatura(AsignaturaModel asignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}//end AsignaturaDao
