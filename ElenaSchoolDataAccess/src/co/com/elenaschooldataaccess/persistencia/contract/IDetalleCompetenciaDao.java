package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschooldataaccess.persistencia.model.DetalleCompetenciaModel;

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
    public boolean deleteDetalleCompetencia(DetalleCompetenciaModel detalle);

    /**
     *
     * @param detalle
     */
    public boolean insertDetalleCompetencia(DetalleCompetenciaModel detalle);

    /**
     *
     * @return
     */
    public List<DetalleCompetenciaModel> readDetalleCompetencia();

    /**
     *
     * @param detalle
     */
    public boolean updateDetalleCompetencia(DetalleCompetenciaModel detalle);

}
