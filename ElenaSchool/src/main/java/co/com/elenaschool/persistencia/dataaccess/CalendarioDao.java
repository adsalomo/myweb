package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.CalendarioModel;
import co.com.elenaschool.persistencia.contract.ICalendarioDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:34
 */
public class CalendarioDao implements ICalendarioDao {

    public CalendarioDao() {

    }

    /**
     *
     * @param calendario
     */
    public boolean deleteCalendario(CalendarioModel calendario) {
        return false;
    }

    /**
     *
     * @param calendario
     */
    public boolean insertCalendario(CalendarioModel calendario) {
        return false;
    }

    /**
     * 
     * @return 
     */
    public List<CalendarioModel> readCalendario() {
        return null;
    }

    /**
     *
     * @param calendario
     */
    public boolean updateCalendario(CalendarioModel calendario) {
        return false;
    }
}//end CalendarioDao
