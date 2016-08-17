/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.elenaschool.business;

import co.com.elenaschool.persistencia.contract.IClaseDao;
import co.com.elenaschool.persistencia.model.ClaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdrianL
 */
@Service
public class ClaseBusiness {
    
    @Autowired
    IClaseDao iClaseDao;
    
    /**
     * Inserta una registro en la entidad clase
     * @param clase
     * @return 
     */
    public boolean insertClase(ClaseModel clase){
        return iClaseDao.insertClase(clase);
    }
    
    /**
     * Actualiaza regtistro en la entidad clase
     * @param claseModel
     * @return 
     */
    public boolean updateClase(ClaseModel claseModel){
        return iClaseDao.updateClase(claseModel);
    }
}
