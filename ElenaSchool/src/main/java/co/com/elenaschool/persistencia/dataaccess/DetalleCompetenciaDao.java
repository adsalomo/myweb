package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.DetalleCompetenciaModel;
import co.com.elenaschool.persistencia.contract.IDetalleCompetenciaDao;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:37
 */
@Component
public class DetalleCompetenciaDao implements IDetalleCompetenciaDao {

    @Override
    public boolean deleteDetalleCompetencia(DetalleCompetenciaModel detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertDetalleCompetencia(DetalleCompetenciaModel detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleCompetenciaModel> readDetalleCompetencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateDetalleCompetencia(DetalleCompetenciaModel detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}//end DetalleCompetenciaDao
