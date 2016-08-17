package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.DetalleCompetenciaModel;
import co.com.elenaschool.persistencia.contract.IDetalleCompetenciaDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:37
 */
public class DetalleCompetenciaDao implements IDetalleCompetenciaDao {

    public DetalleCompetenciaDao() {

    }
    
    /**
     *
     * @param detalle
     */
    public boolean deleteDetalleCompetencia(DetalleCompetenciaModel detalle) {
        return false;
    }

    /**
     *
     * @param detalle
     */
    public boolean insertDetalleCompetencia(DetalleCompetenciaModel detalle) {
        return false;
    }

    /**
     *
     * @return
     */
    public List<DetalleCompetenciaModel> readDetalleCompetencia() {
        return null;
    }

    /**
     *
     * @param detalle
     */
    public boolean updateDetalleCompetencia(DetalleCompetenciaModel detalle) {
        return false;
    }
}//end DetalleCompetenciaDao
