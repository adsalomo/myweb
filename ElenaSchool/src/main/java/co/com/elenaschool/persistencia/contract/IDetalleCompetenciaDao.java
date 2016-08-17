package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.DetalleCompetenciaModel;
import java.sql.SQLException;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:31
 */
public interface IDetalleCompetenciaDao {

    /**
     *
     * @param detalle
     */
    public boolean deleteDetalleCompetencia(DetalleCompetenciaModel detalle) throws SQLException;

    /**
     *
     * @param detalle
     */
    public boolean insertDetalleCompetencia(DetalleCompetenciaModel detalle) throws SQLException;

    /**
     *
     * @return
     */
    public List<DetalleCompetenciaModel> readDetalleCompetencia() throws SQLException;

    /**
     *
     * @param detalle
     */
    public boolean updateDetalleCompetencia(DetalleCompetenciaModel detalle) throws SQLException;

}
