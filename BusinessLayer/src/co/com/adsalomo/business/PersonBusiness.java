package co.com.adsalomo.business;

import co.com.adsalomo.common.model.PersonModel;
import co.com.adsalomo.dataaccess.dao.PersonDao;
import co.com.adsalomo.dataaccess.idao.IPersonDao;
import co.com.adsalomo.transverse.util.Util;
import java.util.List;

/**
 *
 * @author aye
 */
public class PersonBusiness {

    private final IPersonDao personDao;

    public PersonBusiness() {
        personDao = new PersonDao();
    }

    /**
     * Obtiene lista de personas
     *
     * @return
     */
    public List<PersonModel> listPerson() {
        List<PersonModel> list = null;
        try {
            list = personDao.listPerson();
        } catch (Exception ex) {
            Util.writeError(ex.getMessage(), "listPerson");
        }
        return list;
    }

    /**
     * Inserta nuevo registro en la tabla persona
     *
     * @param model
     * @return
     */
    public boolean insertPerson(PersonModel model) {
        boolean res = false;
        try {
            res = personDao.insertPerson(model);
        } catch (Exception ex) {
            Util.writeError(ex.getMessage(), "insertPerson");
        }
        return res;
    }

    /**
     * Actualiza registro en la tabla persona
     *
     * @param model
     * @return
     */
    public boolean updatePerson(PersonModel model) {
        boolean res = false;
        try {
            res = personDao.updatePerson(model);
        } catch (Exception ex) {
            Util.writeError(ex.getMessage(), "updatePerson");
        }
        return res;
    }

    /**
     * Elimina registro en la tabla persona
     *
     * @param model
     * @return
     */
    public boolean deletePerson(PersonModel model) {
        boolean res = false;
        try {
            res = personDao.deletePerson(model);
        } catch (Exception ex) {
            Util.writeError(ex.getMessage(), "deletePerson");
        }
        return res;
    }
}
