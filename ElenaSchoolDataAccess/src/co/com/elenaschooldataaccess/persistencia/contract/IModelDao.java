package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.Model;
import java.util.List;

/**
 *
 * @author AdrianL
 */
public interface IModelDao {
     
    List<Model> readModel(Model model);
}
