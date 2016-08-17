package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.GradoEscolarModel;
import java.sql.SQLException;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:32
 */
public interface IGradoEscolarDao {

    /**
     *
     * @param grado
     */
    public boolean deleteGradoEscolar(GradoEscolarModel grado) throws SQLException;

    /**
     *
     * @param grado
     */
    public boolean insertGradoEscolar(GradoEscolarModel grado) throws SQLException;

    /**
     *
     * @return
     */
    public List<GradoEscolarModel> readGradoEscolar() throws SQLException;

    /**
     *
     * @param grado
     */
    public boolean updateGradoEscolar(GradoEscolarModel grado) throws SQLException;

}
