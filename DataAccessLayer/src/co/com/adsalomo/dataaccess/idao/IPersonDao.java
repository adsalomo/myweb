package co.com.adsalomo.dataaccess.idao;

import co.com.adsalomo.common.model.PersonModel;
import java.util.List;

/**
 * Especificacion del crud de la tabla persona
 * @author aye
 */
public interface IPersonDao {
    
    /**
     * Definicion registro persona
     * @param personModel
     * @return
     */
    boolean insertPerson(PersonModel personModel);
    
    /**
     * Definicion actualizar persona
     * @param personModel
     * @return
     */
    boolean updatePerson(PersonModel personModel);
    
    /**
     * Definicion eliminar persona
     * @param personModel
     * @return
     */
    boolean deletePerson(PersonModel personModel);
    
    /**
     * Definicion listar persona
     * @return 
     */
    List<PersonModel> listPerson();
}
