package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschooldataaccess.persistencia.model.CompetenciaModel;

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
    public boolean deleteCompetencia(CompetenciaModel comp);

    /**
     *
     * @param compentencia
     */
    public boolean insertCompetencia(CompetenciaModel compentencia);

    /**
     * 
     * @return 
     */
    public List<CompetenciaModel> readCompetencia();

    /**
     *
     * @param comp
     */
    public boolean updateCompetencia(CompetenciaModel comp);

}
