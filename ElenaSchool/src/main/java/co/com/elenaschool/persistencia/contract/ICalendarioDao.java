package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.CalendarioModel;
import java.sql.SQLException;
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
    public boolean deleteCalendario(CalendarioModel calendario) throws SQLException;

    /**
     *
     * @param calendario
     */
    public boolean insertCalendario(CalendarioModel calendario) throws SQLException;

    /**
     *
     * @return
     */
    public List<CalendarioModel> readCalendario() throws SQLException;

    /**
     *
     * @param calendario
     */
    public boolean updateCalendario(CalendarioModel calendario) throws SQLException;

}
