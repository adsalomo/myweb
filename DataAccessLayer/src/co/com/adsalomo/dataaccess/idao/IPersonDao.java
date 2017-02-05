/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.adsalomo.dataaccess.idao;

import co.com.adsalomo.common.model.PersonModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aye
 */
public interface IPersonDao {
    
    /**
     * Definicion registro persona
     * @param personModel
     * @return
     * @throws SQLException 
     */
    boolean insertPerson(PersonModel personModel) throws SQLException;
    
    /**
     * Definicion actualizar persona
     * @param personModel
     * @return
     * @throws SQLException 
     */
    boolean updatePerson(PersonModel personModel) throws SQLException;
    
    /**
     * Definicion eliminar persona
     * @param personModel
     * @return
     * @throws SQLException 
     */
    boolean deletePerson(PersonModel personModel) throws SQLException;
    
    /**
     * Definicion listar persona
     * @return
     * @throws SQLException 
     */
    List<PersonModel> listPerson() throws SQLException;
}
