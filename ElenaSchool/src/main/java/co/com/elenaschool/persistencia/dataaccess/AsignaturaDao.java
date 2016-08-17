package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.AsignaturaModel;
import co.com.elenaschool.persistencia.contract.IAsignaturaDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:32
 */
public class AsignaturaDao implements IAsignaturaDao {

    public AsignaturaDao() {

    }

    /**
     *
     * @param asignatura
     */
    public boolean deleteAsignatura(AsignaturaModel asignatura) {
        return false;
    }

    /**
     *
     * @param asignatura
     */
    public boolean insertAsignatura(AsignaturaModel asignatura) {
        return false;
    }

    /**
     * 
     * @return 
     */
    public List<AsignaturaModel> readAsignatura() {
        return null;
    }

    /**
     *
     * @param asignatura
     */
    public boolean updateAsignatura(AsignaturaModel asignatura) {
        return false;
    }
}//end AsignaturaDao
