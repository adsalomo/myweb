package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.Model;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface de definición clase dynamic
 * @author AdrianL
 */
public interface IModelDao {
     
    List<Model> getEstructura(Model model) throws SQLException;
    
    List<Object> getConsulta(String query) throws SQLException;
    
    boolean updateModel(String query) throws SQLException;
    
}
