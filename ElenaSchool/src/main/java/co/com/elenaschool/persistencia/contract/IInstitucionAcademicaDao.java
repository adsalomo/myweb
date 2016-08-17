package co.com.elenaschool.persistencia.contract;

import co.com.elenaschool.persistencia.model.InstitucionAcademicaModel;
import java.sql.SQLException;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:38:34
 */
public interface IInstitucionAcademicaDao {

    /**
     *
     * @param inst
     */
    public boolean deleteInstitucionAcademicaModel(InstitucionAcademicaModel inst) throws SQLException;

    /**
     *
     * @param inst
     */
    public boolean insertInstitucionAcademicaModel(InstitucionAcademicaModel inst) throws SQLException;

    /**
     *
     * @return @throws SQLException
     */
    public List<InstitucionAcademicaModel> readInstitucionAcademicaModel() throws SQLException;

    /**
     *
     * @param inst
     */
    public boolean updateInstitucionAcademicaModel(InstitucionAcademicaModel inst) throws SQLException;

}
