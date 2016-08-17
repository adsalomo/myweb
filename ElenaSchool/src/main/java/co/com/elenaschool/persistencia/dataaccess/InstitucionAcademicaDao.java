package co.com.elenaschool.persistencia.dataaccess;

import co.com.elenaschool.persistencia.model.InstitucionAcademicaModel;
import co.com.elenaschool.persistencia.contract.IInstitucionAcademicaDao;
import java.util.List;

/**
 * @author AdrianL
 * @version 1.0
 * @created 17-ago-2016 08:41:39
 */
public class InstitucionAcademicaDao implements IInstitucionAcademicaDao {

    public InstitucionAcademicaDao() {

    }

    /**
     *
     * @param inst
     */
    public boolean deleteInstitucionAcademicaModel(InstitucionAcademicaModel inst) {
        return false;
    }

    /**
     *
     * @param inst
     */
    public boolean insertInstitucionAcademicaModel(InstitucionAcademicaModel inst) {
        return false;
    }

    /**
     * 
     * @return 
     */
    public List<InstitucionAcademicaModel> readInstitucionAcademicaModel() {
        return null;
    }

    /**
     *
     * @param inst
     */
    public boolean updateInstitucionAcademicaModel(InstitucionAcademicaModel inst) {
        return false;
    }
}//end InstitucionAcademicaDao
