package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.PeriodoAcademicoModel;
import co.com.elenaschool.persistencia.contract.IPeriodoAcademicoDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:41
 */
@Component
public class PeriodoAcademicoDao implements IPeriodoAcademicoDao {

    @Override
    public boolean deletePeriodoAcademico(PeriodoAcademicoModel periodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertPeriodoAcademico(PeriodoAcademicoModel periodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PeriodoAcademicoModel> readPeriodoAcademico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePeriodoAcademico(PeriodoAcademicoModel periodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}//end PeriodoAcademicoDao
