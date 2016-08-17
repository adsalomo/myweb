package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.PeriodoAcademicoModel;
import co.com.elenaschool.persistencia.contract.IPeriodoAcademicoDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:41
 */
public class PeriodoAcademicoDao implements IPeriodoAcademicoDao {

    public PeriodoAcademicoDao() {

    }

    /**
     *
     * @param periodo
     */
    public boolean deletePeriodoAcademico(PeriodoAcademicoModel periodo) {
        return false;
    }

    /**
     *
     * @param periodo
     */
    public boolean insertPeriodoAcademico(PeriodoAcademicoModel periodo) {
        return false;
    }

    /**
     *
     * @return
     */
    public List<PeriodoAcademicoModel> readPeriodoAcademico() {
        return null;
    }

    /**
     *
     * @param periodo
     */
    public boolean updatePeriodoAcademico(PeriodoAcademicoModel periodo) {
        return false;
    }
}//end PeriodoAcademicoDao
