package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.InstitucionAcademicaModel;
import co.com.elenaschool.persistencia.contract.IInstitucionAcademicaDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:39
 */
@Component
public class InstitucionAcademicaDao implements IInstitucionAcademicaDao {

    @Override
    public boolean deleteInstitucionAcademicaModel(InstitucionAcademicaModel inst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertInstitucionAcademicaModel(InstitucionAcademicaModel inst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InstitucionAcademicaModel> readInstitucionAcademicaModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateInstitucionAcademicaModel(InstitucionAcademicaModel inst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}//end InstitucionAcademicaDao
