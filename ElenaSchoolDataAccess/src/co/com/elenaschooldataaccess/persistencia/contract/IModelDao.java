package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.Model;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface de definición clase dynamic
 * @author AdrianL
 */
public interface IModelDao {
     
    List<Model> getStructureTable(Model model) throws SQLException;
    
    List<Object> getQuery(String query) throws SQLException;
    
    boolean updateModel(String query) throws SQLException;
    
    int getMaxTable(String query) throws SQLException;
    
    String getMaxCodeTable(String query) throws SQLException;
    
}
