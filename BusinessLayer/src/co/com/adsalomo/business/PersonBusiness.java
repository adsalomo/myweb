/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.adsalomo.business;

import co.com.adsalomo.common.model.PersonModel;
import co.com.adsalomo.dataaccess.dao.PersonDao;
import co.com.adsalomo.dataaccess.idao.IPersonDao;
import co.com.adsalomo.transverse.util.Util;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aye
 */
public class PersonBusiness {
    private final IPersonDao personDao;
    
    public PersonBusiness(){
        personDao = new PersonDao();
    }
    
    /**
     * Obtiene lista de personas
     * @return 
     */
    public List<PersonModel> listPerson(){
        List<PersonModel> list = null;
        try{
            list = personDao.listPerson();
        } catch (SQLException ex){
            Util.writeError(ex.getMessage(), "listPerson");
        } catch (Exception ex){
            Util.writeError(ex.getMessage(), "listPerson");
        }
        return list;
    }
    
    /**
     * Inserta nuevo registro en la tabla persona
     * @param model
     * @return 
     */
    public boolean insertPerson(PersonModel model){
        boolean res = false;
        try{
            res = personDao.insertPerson(model);
        }catch (SQLException ex){
            Util.writeError(ex.getMessage(), "insertPerson");
        } catch (Exception ex){
            Util.writeError(ex.getMessage(), "insertPerson");
        }
        return res;
    }
    
    /**
     * Actualiza registro en la tabla persona
     * @param model
     * @return 
     */
    public boolean updatePerson(PersonModel model){
        boolean res = false;
        try{
            res = personDao.updatePerson(model);
        }catch (SQLException ex){
            Util.writeError(ex.getMessage(), "updatePerson");
        } catch (Exception ex){
            Util.writeError(ex.getMessage(), "updatePerson");
        }
        return res;
    }
    
    /**
     * Elimina registro en la tabla persona
     * @param model
     * @return 
     */
    public boolean deletePerson(PersonModel model){
        boolean res = false;
        try{
            res = personDao.deletePerson(model);
        }catch (SQLException ex){
            Util.writeError(ex.getMessage(), "deletePerson");
        } catch (Exception ex){
            Util.writeError(ex.getMessage(), "deletePerson");
        }
        return res;
    }
}
