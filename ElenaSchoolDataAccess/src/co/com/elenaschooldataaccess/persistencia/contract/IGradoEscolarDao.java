package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschooldataaccess.persistencia.model.GradoEscolarModel;

import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:32
 */
public interface IGradoEscolarDao {

    /**
     *
     * @param grado
     */
    public boolean deleteGradoEscolar(GradoEscolarModel grado);

    /**
     *
     * @param grado
     */
    public boolean insertGradoEscolar(GradoEscolarModel grado);

    /**
     *
     * @return
     */
    public List<GradoEscolarModel> readGradoEscolar();

    /**
     *
     * @param grado
     */
    public boolean updateGradoEscolar(GradoEscolarModel grado);

}
