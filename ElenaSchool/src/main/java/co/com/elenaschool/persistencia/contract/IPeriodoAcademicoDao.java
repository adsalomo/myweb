package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.PeriodoAcademicoModel;
import java.sql.SQLException;
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
    public boolean deletePeriodoAcademico(PeriodoAcademicoModel periodo) throws SQLException;

    /**
     *
     * @param periodo
     */
    public boolean insertPeriodoAcademico(PeriodoAcademicoModel periodo) throws SQLException;

    /**
     *
     * @return
     */
    public List<PeriodoAcademicoModel> readPeriodoAcademico() throws SQLException;

    /**
     *
     * @param periodo
     */
    public boolean updatePeriodoAcademico(PeriodoAcademicoModel periodo) throws SQLException;

}
