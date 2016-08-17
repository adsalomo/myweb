package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.GradoEscolarModel;
import co.com.elenaschool.persistencia.contract.IGradoEscolarDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:38
 */
public class GradoEscolarDao implements IGradoEscolarDao {

    public GradoEscolarDao() {

    }

    /**
     *
     * @param grado
     */
    public boolean deleteGradoEscolar(GradoEscolarModel grado) {
        return false;
    }

    /**
     *
     * @param grado
     */
    public boolean insertGradoEscolar(GradoEscolarModel grado) {
        return false;
    }

    /**
     *
     * @return
     */
    public List<GradoEscolarModel> readGradoEscolar() {
        return null;
    }

    /**
     *
     * @param grado
     */
    public boolean updateGradoEscolar(GradoEscolarModel grado) {
        return false;
    }
}//end GradoEscolarDao
