package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.ClaseModel;

import java.util.List;

/**
 * @author aye
 * @version 1.0
 * @created 17-ago-2016 08:38:28
 */
public interface IClaseDao {

    /**
     *
     * @param clase
     */
    public boolean deleteClase(ClaseModel clase);

    /**
     *
     * @param clase
     * @return 
     */
    public boolean insertClase(ClaseModel clase);

    /**
     * 
     * @return 
     */
    public List<ClaseModel> readClase();

    /**
     *
     * @param clase
     */
    public boolean updateClase(ClaseModel clase);

}
