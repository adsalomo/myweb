package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.CompetenciaModel;
import co.com.elenaschool.persistencia.contract.ICompetenciaDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:36
 */
@Component
public class CompetenciaDao implements ICompetenciaDao {

    @Override
    public boolean deleteCompetencia(CompetenciaModel comp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertCompetencia(CompetenciaModel compentencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompetenciaModel> readCompetencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateCompetencia(CompetenciaModel comp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}//end CompetenciaDao
