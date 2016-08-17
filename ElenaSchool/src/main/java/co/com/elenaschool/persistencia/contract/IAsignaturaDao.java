package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.AsignaturaModel;
import java.sql.SQLException;
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
    public boolean deleteAsignatura(AsignaturaModel asignatura) throws SQLException;

    /**
     *
     * @param asignatura
     */
    public boolean insertAsignatura(AsignaturaModel asignatura) throws SQLException;

    /**
     *
     * @return
     */
    public List<AsignaturaModel> readAsignatura() throws SQLException;

    /**
     *
     * @param asignatura
     */
    public boolean updateAsignatura(AsignaturaModel asignatura) throws SQLException;

}
