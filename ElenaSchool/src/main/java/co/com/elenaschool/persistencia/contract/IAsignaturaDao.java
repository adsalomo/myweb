package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.AsignaturaModel;

import java.util.List;

/**
 * Interface que define los m�todos del dao Asignatura
 *
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:26
 */
public interface IAsignaturaDao {

    /**
     *
     * @param asignatura
     */
    boolean deleteAsignatura(AsignaturaModel asignatura);

    /**
     *
     * @param asignatura
     */
    boolean insertAsignatura(AsignaturaModel asignatura);

    /**
     *
     * @return
     */
    List<AsignaturaModel> readAsignatura();

    /**
     *
     * @param asignatura
     */
    boolean updateAsignatura(AsignaturaModel asignatura);

}
