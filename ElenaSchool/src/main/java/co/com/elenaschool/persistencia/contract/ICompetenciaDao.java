package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.CompetenciaModel;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define operaciones crud dao competencia
 *
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:30
 */
public interface ICompetenciaDao {

    /**
     *
     * @param comp
     */
    public boolean deleteCompetencia(CompetenciaModel comp) throws SQLException;

    /**
     *
     * @param compentencia
     */
    public boolean insertCompetencia(CompetenciaModel compentencia) throws SQLException;

    /**
     * 
     * @return 
     */
    public List<CompetenciaModel> readCompetencia() throws SQLException;

    /**
     *
     * @param comp
     */
    public boolean updateCompetencia(CompetenciaModel comp) throws SQLException;

}
