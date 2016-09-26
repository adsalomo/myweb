package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.Model;
import co.com.elenaschoolmodel.model.QueryModel;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface de definición clase dynamic
 * @author AdrianL
 */
public interface IModelDao {
     
    List<Model> getEstructura(Model model) throws SQLException;
    
    QueryModel getConsulta(QueryModel queryModel) throws SQLException;
}
