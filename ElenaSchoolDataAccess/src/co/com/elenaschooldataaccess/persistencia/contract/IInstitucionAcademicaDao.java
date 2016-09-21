package co.com.elenaschooldataaccess.persistencia.contract;

import co.com.elenaschoolmodel.model.InstitucionAcademicaModel;
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
    public boolean deleteInstitucionAcademicaModel(InstitucionAcademicaModel inst);

    /**
     *
     * @param inst
     */
    public boolean insertInstitucionAcademicaModel(InstitucionAcademicaModel inst);

    /**
     *
     * @return @throws SQLException
     */
    public List<InstitucionAcademicaModel> readInstitucionAcademicaModel();

    /**
     *
     * @param inst
     */
    public boolean updateInstitucionAcademicaModel(InstitucionAcademicaModel inst);

}
