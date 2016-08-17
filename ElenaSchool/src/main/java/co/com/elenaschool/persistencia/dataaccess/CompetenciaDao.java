package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.CompetenciaModel;
import co.com.elenaschool.persistencia.contract.ICompetenciaDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:36
 */
public class CompetenciaDao implements ICompetenciaDao {

    public CompetenciaDao() {

    }

    /**
     *
     * @param comp
     */
    public boolean deleteCompetencia(CompetenciaModel comp) {
        return false;
    }

    /**
     *
     * @param compentencia
     */
    public boolean insertCompetencia(CompetenciaModel compentencia) {
        return false;
    }

    /**
     *
     * @return
     */
    public List<CompetenciaModel> readCompetencia() {
        return null;
    }

    /**
     *
     * @param comp
     */
    public boolean updateCompetencia(CompetenciaModel comp) {
        return false;
    }
}//end CompetenciaDao
