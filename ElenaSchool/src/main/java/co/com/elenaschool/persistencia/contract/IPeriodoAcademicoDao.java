package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.PeriodoAcademicoModel;

import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:36
 */
public interface IPeriodoAcademicoDao {

    /**
     *
     * @param periodo
     */
    public boolean deletePeriodoAcademico(PeriodoAcademicoModel periodo);

    /**
     *
     * @param periodo
     */
    public boolean insertPeriodoAcademico(PeriodoAcademicoModel periodo);

    /**
     *
     * @return
     */
    public List<PeriodoAcademicoModel> readPeriodoAcademico();

    /**
     *
     * @param periodo
     */
    public boolean updatePeriodoAcademico(PeriodoAcademicoModel periodo);

}
