package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.CalendarioModel;

import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 09:12:23
 */
public interface ICalendarioDao {

    /**
     *
     * @param calendario
     */
    public boolean deleteCalendario(CalendarioModel calendario);

    /**
     *
     * @param calendario
     */
    public boolean insertCalendario(CalendarioModel calendario);

    /**
     *
     * @return
     */
    public List<CalendarioModel> readCalendario();

    /**
     *
     * @param calendario
     */
    public boolean updateCalendario(CalendarioModel calendario);

}
